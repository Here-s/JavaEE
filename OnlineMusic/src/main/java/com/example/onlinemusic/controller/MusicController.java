package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.LoveMusicMapper;
import com.example.onlinemusic.mapper.MusicMapper;
import com.example.onlinemusic.model.Music;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Value("${music.local.path}")
    private String SAVE_PATH;

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private LoveMusicMapper loveMusicMapper;

    @RequestMapping("/upload")
    public ResponseBodyMessage<Boolean> insertMusic(@RequestParam String singer,
                    @RequestParam("filename") MultipartFile file,
                    HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                //上传成功之后，跳转到音乐列表页面
                response.sendRedirect("/list.html");
                return new ResponseBodyMessage<>(0, "数据库上传成功！", true);
            } else {
                return new ResponseBodyMessage<>(-1,"数据库上传失败！",false);
            }
        } catch (BindingException e) {
            dest.delete();
            return new ResponseBodyMessage<>(-1,"数据库上传失败！",false);
        }

    }

    /**
     * 播放音乐  /music/get?path=xxx.mp3
     * @param path
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity<byte[]> func(String path) {
        File file = new File(SAVE_PATH + path);
        try {
            byte[] a = Files.readAllBytes(file.toPath());
            if (a == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * 删除单个音乐
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ResponseBodyMessage<Boolean> deleteMusicById(@RequestParam String id) {
        //1、检查音乐是否存在
        int Id = Integer.parseInt(id);
        //2、删除，数据库和服务器都要删除
        Music music = musicMapper.findMusicById(Id);
        if (music == null) {
            System.out.println("没有此 id 的音乐");
            return new ResponseBodyMessage<>(-1,"没有找到要删除的音乐",false);
        } else {
            //删除数据库音乐
            int ret = musicMapper.deleteMusicById(Id);
            if (ret == 1) {
                //删除服务器音乐
                int index = music.getUrl().lastIndexOf("=");
                String fileName = music.getUrl().substring(index + 1);
                File file = new File(SAVE_PATH + fileName + ".mp3");
                System.out.println(file);
                if (file.delete()) {
                    //也同步要删除喜欢的 id 里面的音乐
                    loveMusicMapper.deleteLoveMusicByMusicId(Id);
                    return new ResponseBodyMessage<>(0, "服务器音乐删除成功！", true);
                } else {
                    return new ResponseBodyMessage<>(-1,"服务器音乐删除失败！",false);
                }
            } else {
                return new ResponseBodyMessage<>(-1,"数据库当中的音乐没有被删除",false);
            }

        }
    }

    /**
     * 批量删除
     * @param id
     * @return
     */
    @RequestMapping("/deletesel")
    public ResponseBodyMessage<Boolean> deleteSelMusic(@RequestParam("id[]") List<Integer> id) {
        int sum = 0;
        for (int i = 0; i < id.size(); i++) {
            int musicId = id.get(i);
            Music music = musicMapper.findMusicById(musicId);
            if (music == null) {
                System.out.println("没有这个 id 的音乐");
                return new ResponseBodyMessage<>(-1,"没有你要删除的音乐",false);
            }
            int ret = musicMapper.deleteMusicById(musicId);
            if (ret == 1) {
                //删除服务器音乐
                int index = music.getUrl().lastIndexOf("=");
                String fileName = music.getUrl().substring(index + 1);
                File file = new File(SAVE_PATH + fileName + ".mp3");
                if (file.delete()) {
                    sum += ret;
                    //也要同步删除喜欢的 id 里面的内容
                    loveMusicMapper.deleteLoveMusicByMusicId(musicId);
                } else {
                    return new ResponseBodyMessage<>(-1,"服务器音乐删除失败！",false);
                }
            } else {
                return new ResponseBodyMessage<>(-1,"数据库音乐删除失败！",false);
            }
        }
        if (sum == id.size()) {
            System.out.println("全部删除成功！");
            return new ResponseBodyMessage<>(0, "音乐删除成功！", true);
        } else {
            return new ResponseBodyMessage<>(-1,"音乐删除失败！",false);
        }
    }

    @RequestMapping("/findmusic")
    public ResponseBodyMessage<List<Music>> findMusic(@RequestParam(required = false) String musicname) {

        List<Music> musicList = null;
        if (musicname != null) {
            musicList = musicMapper.findMusicByName(musicname);
        } else {
            musicList = musicMapper.findMusic();
        }
        return new ResponseBodyMessage<>(0, "查询到了所有的音乐！", musicList);
    }
}
