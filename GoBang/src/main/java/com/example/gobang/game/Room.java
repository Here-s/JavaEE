package com.example.gobang.game;

import com.example.gobang.GoBangApplication;
import com.example.gobang.mapper.UserMapper;
import com.example.gobang.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.UUID;

//游戏房间
public class Room {
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    //使用字符串类型表示，用来生成唯一值
    private String roomId;

    private User user1;
    private User user2;

    private static final int MAX_ROW = 15;
    private static final int MAX_COL = 15;

    public int getWhiteUser() {
        return whiteUser;
    }

    public void setWhiteUser(int whiteUser) {
        this.whiteUser = whiteUser;
    }

    //先手方
    private int whiteUser;

    //棋盘，使用 0 表示当前位置没有棋子，1 表示玩家1的棋子，2 表示玩家2的棋子
    private int board[][] = new int[MAX_ROW][MAX_COL];

    //用来转换 JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    private OnlineUserManager onlineUserManager;

    private UserMapper userMapper;

    //管理房间
    private RoomManager roomManager;

    public Room() {
        //使用唯一字符串表示房间 id
        //使用 UUID 作为房间 ID
        roomId = UUID.randomUUID().toString();

        //通过 入口类的 context 来获取 RoomManager 和 OnlineUserManager
        onlineUserManager = GoBangApplication.context.getBean(OnlineUserManager.class);
        roomManager = GoBangApplication.context.getBean(RoomManager.class);
        userMapper = GoBangApplication.context.getBean(UserMapper.class);
    }

    //处理落子操作   记录落子位置，判断胜负，返回客户端
    public void putChess(String reqJson) throws IOException {
        //记录落子位置
        GameRequest request = objectMapper.readValue(reqJson,GameRequest.class);
        GameResponse response = new GameResponse();
        //记录这个子是玩家1，还是玩家2，来往数组当中写 1 和 2
        int chess = request.getUserId() == user1.getUserid() ? 1 : 2;
        int row = request.getRow();
        int col = request.getCol();
        if (board[row][col] != 0) {
            //在服务器再次进行判断
            System.out.println("当前位置 (" + row + ", " + col + ") 已经有棋子了!");
            return;
        }
        //设置棋子
        board[row][col] = chess;

        //输出棋盘，方便判断
        printBoard();

        //判断输赢
        int winner = checkWinner(row, col, chess);
        //返回给客户端
        response.setMessage("putChess");
        response.setUserId(request.getUserId());
        response.setRow(row);
        response.setCol(col);
        response.setWinner(winner);

        //获取到用户的 WebSocketSession
        WebSocketSession session1 = onlineUserManager.getFromGameRoom(user1.getUserid());
        WebSocketSession session2 = onlineUserManager.getFromGameRoom(user2.getUserid());
        //如果玩家已经下线
        if (session1 == null) {
            //玩家1 下线，玩家2 获胜
            response.setWinner(user2.getUserid());
            System.out.println("玩家1 掉线！");
        }
        if (session2 == null) {
            //玩家2 下线，玩家1 获胜
            response.setWinner(user1.getUserid());
            System.out.println("玩家2 掉线！");
        }
        //把响应传回去
        String respJson = objectMapper.writeValueAsString(response);
        if (session1 != null) {
            session1.sendMessage(new TextMessage(respJson));
        }
        if (session2 != null) {
            session2.sendMessage(new TextMessage(respJson));
        }
        //如果胜负已分，就销毁房间
        if (response.getWinner() != 0) {
            System.out.println("游戏结束，房间即将销毁！roomId = " + roomId + " 获胜的人是：" + response.getWinner());
            //更新数据
            int winnerId = response.getWinner();
            int loseUserId = response.getWinner() == user1.getUserid() ? user2.getUserid() : user1.getUserid();
            userMapper.userWin(winnerId);
            userMapper.userLose(loseUserId);

            //销毁房间
            roomManager.remove(roomId, user1.getUserid(), user2.getUserid());
        }
    }

    private void printBoard() {
        //打印出棋盘
        System.out.println("打印棋盘信息");
        System.out.println("===============================================");
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============================================");
    }

    //判断输赢，谁赢了返回谁的 userId，都没赢就返回 0
    private int checkWinner(int row, int col, int chess) {
        //判断所有行
        for (int c = col - 4; c <= col; c++) {
            //判断这五个棋子是不是连在一起，而且和玩家的棋子还得一样
            try {
                if (board[row][c] == chess
                        && board[row][c + 1] == chess
                        && board[row][c + 2] == chess
                        && board[row][c + 3] == chess
                        && board[row][c + 4] == chess) {
                    //现在就胜负已分了
                    return chess == 1? user1.getUserid() : user2.getUserid();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //出现异常，直接忽略就好
                continue;
            }
        }

        //判断所有列
        for (int r = row - 4; r <= row; r++) {
            try {
                if (board[r][col] == chess
                    && board[r + 1][col] == chess
                    && board[r + 2][col] == chess
                    && board[r + 3][col] == chess
                    && board[r + 4][col] == chess) {
                    return chess == 1? user1.getUserid() : user2.getUserid();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        //判断左对角线
        for (int r = row - 4, c = col - 4; r <= row && c <= col; r++, c++) {
            try {
                if (board[r][c] == chess
                    && board[r + 1][c + 1] == chess
                    && board[r + 2][c + 2] == chess
                    && board[r + 3][c + 3] == chess
                    && board[r + 4][c + 4] == chess) {
                    return chess == 1? user1.getUserid() : user2.getUserid();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        //判断右对角线
        for (int r = row - 4, c = col + 4; r <= row && c >= col; r++, c--) {
            try {
                if (board[r][c] == chess
                        && board[r + 1][c - 1] == chess
                        && board[r + 2][c - 2] == chess
                        && board[r + 3][c - 3] == chess
                        && board[r + 4][c - 4] == chess) {
                    return chess == 1? user1.getUserid() : user2.getUserid();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return 0;
    }
}
