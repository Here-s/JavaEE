import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这个注解就是 把当前的 HelloServlet 这个类，和 HTTP 请求中的 URL 里面路径带有 /hello
// 这样的请求，给关联起来了
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //这个是让服务器在 控制台 上面打印 hello word
        System.out.println("hello word");
        //把这个 字符串 放入 HTTP 响应的 body 中，浏览器就会把 body 的内容写到
        resp.getWriter().write("hello word");
    }
}
