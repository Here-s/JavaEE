import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理用户请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //判定用户名和密码是否正确，正常是通过数据库来判断，此处直接写死：zhangsan  123
        if ("zhangsan".equals(username) && "123".equals(password)) {
            //登陆成功，重定向
            //创建会话，并保存必要的身份信息
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("username",username);

            //初始情况下，登录次数设为 0
            httpSession.setAttribute("count",0);
            resp.sendRedirect("index");
        } else {
            //登陆失败
            resp.getWriter().write("login failed!");
        }
    }
}
