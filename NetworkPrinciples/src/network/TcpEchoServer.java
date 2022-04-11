package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//一个服务器，应该和多个端口建立连接，这里的只能接受一个客户端，因为和客户端交互的前提是，先调用 accept，
// 因为之前写的代码，调用一次 accept ，就陷入了 while 循环，只有没有输入才会跳出，
// 所以就调用不了 accept，就不能一直与多个端口建立连接。所以就可以使用 多线程 来实现多个端口
//因为 TCP 既要处理连接，又要处理一个连接中的若干次请求，就需要两个循环，里层循环，就会影响到外层循环的进度
// 所以使用 多线程 来的话，就可以了
public class TcpEchoServer {
    //listen 本意是 监听 但是在 Java socket 当中体验不出来。因为操作系统原生的 API 有一个操做叫做 listen
    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            //由于 TCP 是有链接的，不能一上来就读数据，而要先建立连接（接电话）
            //accept 就是在“接电话” 接电话的前提是，有人给你打电话，如果当前没有客户端尝试建立连接，此处的 accept 就会阻塞
            //accept 返回了一个 socket 对象，称为 clientSocket 后续和客户端之间的沟通，都是通过 clientSocket 来完成的
            // 进一步讲：serverSocket 就干了一件事，接电话。
            Socket clientSocket = serverSocket.accept();
            processConnection(clientSocket);
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d] 客户端建立连接\n",clientSocket.getInetAddress().toString(),clientSocket.getPort());
        //接下来处理请求和响应  这里的针对 TCP socket 的读写 就和 文件读写 是一模一样的。
        try (InputStream inputStream = clientSocket.getInputStream()) {
            try (OutputStream outputStream = clientSocket.getOutputStream()) {
                //循环的处理每个请求，然后分别返回响应
                Scanner scanner = new Scanner(inputStream);
                while (true) {
                    //1、读取请求
                    if (!scanner.hasNext()) {
                        System.out.printf("[%s:%d] 客户端断开连接",clientSocket.getInetAddress().toString(),clientSocket.getPort());
                        break;
                    }
                    //此处用 scanner 更方便，如果不用 Scanner 的话，就用原生的 InputStream 的 read 也是可以的
                    String request = scanner.next();
                    //2、根据请求，计算响应
                    String response = process(request);
                    //3、把响应返回客户端，为了方便起见，可以使用 PrintWriter 把 OutputStream 包裹一下
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(response);
                    //刷新缓冲区，如果不刷新的话，可能客户端就不能第一时间看到响应结果
                    printWriter.flush();
                    System.out.printf("[%s:%d] req: %s, resp: %s\n",clientSocket.getInetAddress().toString(),
                            clientSocket.getPort(),request,response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //此处要有一个关闭操作
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}
