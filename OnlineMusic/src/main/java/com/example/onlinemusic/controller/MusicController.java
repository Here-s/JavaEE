package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.MusicMapper;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import com.example.onlinemusic.tools.GetFileType;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Value("${music.local.path}")
    private String SAVE_PATH;

    @Autowired
    private MusicMapper musicMapper;

    @RequestMapping("/upload")
    public ResponseBodyMessage<Boolean> insertMusic(@RequestParam String singer,
                                                    @RequestParam("filename") MultipartFile file,
                                                    HttpServletRequest request) throws IOException {
        //检查是否登录
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("没有登录！");
            return new ResponseBodyMessage<>(-1,"请登录后上传",false);
        }
        //获取文件名称和类型
        String fileNameAndType = file.getOriginalFilename();
        System.out.println("文件名称" + fileNameAndType);

        //上传文件
        String path = SAVE_PATH + fileNameAndType;

        File dest = new File(path);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try {
            file.transferTo(dest);
            System.out.println(path);
            String fileName = GetFileType.getFileHead(path);
            if (fileName == null) {
                dest.delete();
                return new ResponseBodyMessage<>(-1,"上传的不是 mp3 音频文件！",false);
            }
            if (fileName.equals("mp3") || fileName.equals("MP3") ||
                    fileName.equals("flac") || fileName.equals("FLAC")) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBodyMessage<>(-1,"服务器上传失败！",false);
        }

        //进行数据库的上传
        //1、准备数据
        int index = fileNameAndType.lastIndexOf(".");
        String title = fileNameAndType.substring(0, index);

        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        int userid = user.getId();

        //url 用来播放音乐，就是 http 请求
        String url = "/music/get?path=" + title;
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        //2、调用 insert
        try {
            int ret = 0;
            ret = musicMapper.insert(title,singer,time,url,userid);
            if (ret == 1) {
                return new ResponseBodyMessage<>(0, "数据库上传成功！", true);
            } else {
                return new ResponseBodyMessage<>(-1,"数据库上传失败！",false);
            }
        } catch (BindingException e) {
            dest.delete();
            return new ResponseBodyMessage<>(-1,"数据库上传失败！",false);
        }

    }

    @RequestMapping("/get")
    public ResponseEntity<byte[]> func() {
        byte[] a = {97,98,99,100};
        return ResponseEntity.internalServerError().build();
    }
}
