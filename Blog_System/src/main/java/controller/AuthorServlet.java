package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorInfo")
public class AuthorServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf8");
        //通过这个方法来获取到博客的作者信息
        String param = req.getParameter("blogId");
        if (param == null || "".equals(param)) {
            //参数缺少了
            resp.getWriter().write("{\"ok\": false,\"reason\": \"参数缺失！\"}");
            return;
        }
        //根据当前 blogId 在数据库当中进行查找，找到对应的 blog 对象，再进一步根据 blog 对象，找到作者
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(param));
        if (blog == null) {
            resp.getWriter().write("{\"ok\": false,\"reason\": \"要查询的博客不存在\"}");
            return;
        }

        //根据 blog 对象，查询用户对象
        UserDao userDao = new UserDao();
        User author = userDao.selectById(blog.getUserId());
        if (author == null) {
            resp.getWriter().write("{\"ok\": false,\"reason\": \"要查询的用户不存在\"}");
            return;
        }

        //把 author 对象返回到浏览器，要把密码改掉
        author.setPassword("");
        resp.getWriter().write(objectMapper.writeValueAsString(author));
    }
}
