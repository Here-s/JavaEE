import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回 302 重定向响应，然后跳转到 搜狗
//        resp.setStatus(302);
//        resp.setHeader("Location","https://www.sogou.com");

        //Servlet 提供了一个更简单的重定向的方法
        resp.sendRedirect("https://www.sogou.com");
    }
}
