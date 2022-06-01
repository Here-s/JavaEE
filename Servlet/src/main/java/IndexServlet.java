import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回一个主页，需要获得 username，通过 httpSession 获得

        //因为之前 登录过程中，已经创建过会话了，所以直接 false 就好了
        HttpSession session = req.getSession(false);
        String username = (String) session.getAttribute("username");
        Integer count = (Integer) session.getAttribute("count");
        count += 1;
        //把自增之后的值在写回去
        session.setAttribute("count", count);
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("<h3>欢迎你! " + username + " 这是第 " + count + "次访问主页" + "</h3>");
    }
}
