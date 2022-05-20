import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class User {
    public int userId;
    public int classId;
}
@WebServlet("/postJson")
public class PostJsonServlet extends HttpServlet {
    //创建一个 jackson 的核心对象
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //读取 body 的请求，然后使用 ObjectMapper 来解析成需要的对象
        // readValue 就是把 JSON 格式的字符串，转成 Java 的对象，第一个参数是对 哪个字符串转换
        // 第二个参数，表示要把这个 JSON 格式的字符串，转成哪个 Java 对象
        User user = objectMapper.readValue(req.getInputStream(),User.class);
        resp.getWriter().write("userId: "+user.userId+" classId: "+user.classId);
    }
}
