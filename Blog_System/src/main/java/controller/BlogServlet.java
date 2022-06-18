package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            //用户未登录，不能提交博客
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前用户未登录，不能提交博客");
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //当前用户未登录，不能提交
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前用户未登录，不能提交博客！");
            return;
        }
        //先指定好请求按照哪种编码解析
        req.setCharacterEncoding("utf8");
        //先从请求当中取出参数：标题和正文
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || "".equals(title) || content == null || "".equals(content)) {
            //说明请求的参数不对
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("提交博客失败，缺少必要的参数");
            return;
        }
        //构造 blog 对象，把信息填进去，然后插入数据库
        // 这里设置的属性，标题，内容，作者信息。postTime 和 blogId 都是自动生成的
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        //UserId 就是提交博客的用户 Id
        blog.setUserId(user.getUserId());
        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        //新增完成之后，重定向到博客列表页
        resp.sendRedirect("blog.html");
    }
}
