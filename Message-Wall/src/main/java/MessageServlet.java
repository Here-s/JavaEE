import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Message {
    public String from;
    public String to;
    public String message;
}

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    //改成数据库，就不需要这个变量了
    //public List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理提交消息请求
        Message message = objectMapper.readValue(req.getInputStream(),Message.class);
        //最简单的保存方法就是保存到内存当中
        //messages.add(message);
        //通过 ContentType 告诉页面，返回的数据是 json 格式，有了 这样的声明，就会把 jQuery Ajax
        // 字符串转化为 js 对象
        save(message);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write("{\"ok\": true}");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取消息列表。 把消息列表返回到 客户端就可以了，通过 objectMapper 把 Java 对象转化为 json
        List<Message> messages = load();
        String jsonString = objectMapper.writeValueAsString(messages);
        System.out.println("jsonString: "+jsonString);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(jsonString);
    }

    private void save(Message message) {
        //把一条数据保存到数据库当中
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //建立连接
            connection = DBUtil.getConnection();
            //构造SQL
            String sql = "insert into message values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,message.from);
            statement.setString(2,message.to);
            statement.setString(3,message.message);
            //执行 sql
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    private List<Message> load() {
        //从数据库当中获取到所有的消息
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> messages = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from message";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message. from = resultSet.getString("from");
                message.to = resultSet.getString("to");
                message.message = resultSet.getString("message");
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return messages;
    }
}
