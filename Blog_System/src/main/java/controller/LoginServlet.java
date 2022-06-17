package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        //获取到请求中的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            //请求数据缺失，登陆失败
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前的用户名或密码为空");
            return;
        }
        //和 数据库 当中的请求比较
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            //用户没有查到，或者密码不对，登陆失败
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户名或密码错误");
            return;
        }
        //比较通过，就创建会话
        HttpSession session = req.getSession(true);
        //把刚才的用户信息，存储到会话中
        session.setAttribute("user", user);

        //返回对象报文，再转到博客页
        resp.sendRedirect("blog.html");
    }

    //这个方法用来让前端检测当前的登陆状态
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        HttpSession session = req.getSession(false);
        if (session == null) {
            //检测会话是否存在，不存在说明未登录
            User user = new User();
            resp.getWriter().write(objectMapper.writeValueAsString(user));
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //如果有会话，但是没有 user 对象，也视为未登录
            user = new User();
            resp.getWriter().write(objectMapper.writeValueAsString(user));
            return;
        }
        //已经在登录状态
        user.setPassword("");
        resp.getWriter().write(objectMapper.writeValueAsString(user));
    }
}
