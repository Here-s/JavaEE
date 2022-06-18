package controller;

import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先判断当前用户也没有登陆
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前尚未登陆，不能删除");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前尚未登陆，不能删除");
            return;
        }
        //获取参数中的 blogId
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前 blogId 参数不正确，不能删除");
            return;
        }
        //获取要删除的博客信息
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if (blog == null) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前要删除的博客不存在，不能删除");
            return;
        }
        //判断当前用户是不是博客的作者
        if (user.getUserId() != blog.getUserId()) {
            //在前端也处理过，保险起见，再校验一次
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前登录的用户不是作者，不能删除");
            return;
        }
        //确认通过，删除博客
        blogDao.delete(Integer.parseInt(blogId));

        //删完之后，重定向
        resp.sendRedirect("blog.html");
    }
}
