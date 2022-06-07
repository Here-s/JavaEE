package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//处理 blog 路径对应的请求
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    //获取数据库当中的博客列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先尝试获取 req 当中的 blogId 参数，有参数的话，就是博客详情页
        // 没有参数的话，就是博客列表
        resp.setContentType("application/json; charset=utf8");
        BlogDao blogDao = new BlogDao();
        String param = req.getParameter("blogId");
        if (param == null) {
            //没有参数，获取博客列表
            List<Blog> blogs = blogDao.selectAll();
            //把 blogs 转成 JSON 格式
            String respJson = objectMapper.writeValueAsString(blogs);

            resp.getWriter().write(respJson);
        } else {
            //存在参数，获取博客详情
            int blogId = Integer.parseInt(param);
            Blog blog = blogDao.selectOne(blogId);
            String respJson = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(respJson);;
        }
    }
}
