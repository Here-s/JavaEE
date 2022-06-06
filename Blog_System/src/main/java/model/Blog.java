package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//每个 model.Blog 对象，对应 blog 表里的一条记录
public class Blog {
    private int blogId;
    private int userId;
    private String title;
    private String content;
    private Timestamp postTime;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Timestamp getPostTime() {
//        return postTime;
//    }

    //直接格式化时间
    public String getPostTime() {
        //来完成时间戳格式化日期时间的转化，在构造方法当中，指定转换格式，然后调用 format 转换
        // 时间格式一定要查一下，怎么写合适
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss ");
        return simpleDateFormat.format(postTime);
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }
}
