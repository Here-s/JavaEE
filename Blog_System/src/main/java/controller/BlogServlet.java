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
        //从数据库当作查询到博客列表，然后转成 json 格式
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.selectAll();
        //把 blogs 转成 JSON 格式
        String respJson = objectMapper.writeValueAsString(blogs);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(respJson);
    }
}
