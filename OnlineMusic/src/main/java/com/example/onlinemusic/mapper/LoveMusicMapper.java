package com.example.onlinemusic.mapper;


import com.example.onlinemusic.model.Music;

public interface LoveMusicMapper {

    /**
     * 查询喜欢的音乐
     * @return
     */
    Music findLoveMusic(int userid, int musicid);

    /**
     * 收藏音乐
     * @return
     */
    boolean insertLoveMusic(int userId, int musicId);
}
