package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.LoveMusicMapper;
import com.example.onlinemusic.model.Music;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/lovemusic")
public class LoveMusicController {

    @Autowired
    private LoveMusicMapper loveMusicMapper;

    @RequestMapping("/likemusic")
    public ResponseBodyMessage<Boolean> likeMusic(@RequestParam String id, HttpServletRequest request) {

        int musicId = Integer.parseInt(id);
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("没有登录！");
            return new ResponseBodyMessage<>(-1,"请登录后再喜欢！",false);
        }

        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        int userId = user.getId();
        System.out.println("userId：" + userId);

        Music music = loveMusicMapper.findLoveMusic(userId, musicId);
        if (music != null) {
            return new ResponseBodyMessage<>(-1,"之前喜欢过这个音乐",false);
        }

        boolean effect = loveMusicMapper.insertLoveMusic(userId, musicId);
        if (effect) {
            return new ResponseBodyMessage<>(0,"已添加到我的喜欢！",true);
        } else {
            return new ResponseBodyMessage<>(-1,"添加到我的喜欢失败！",false);
        }
    }

    @RequestMapping("/findlovemusic")
    public ResponseBodyMessage<List<Music>> findLoveMusic (@RequestParam(required = false)
               String musicName, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("没有登录！");
            return new ResponseBodyMessage<>(-1,"请登录后再查找！",null);
        }

        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        int userId = user.getId();
        System.out.println("userId：" + userId);

        List<Music> musicList = null;
        if (musicName == null) {
            musicList = loveMusicMapper.findLoveMusicByUserId(userId);
        } else {
            musicList = loveMusicMapper.findLoveMusicByKeyAndUID(musicName, userId);
        }
        return new ResponseBodyMessage<>(0,"已查询到歌曲信息！",musicList);
    }
}
