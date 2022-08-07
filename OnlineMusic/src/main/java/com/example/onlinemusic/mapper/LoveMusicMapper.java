package com.example.onlinemusic.mapper;


import com.example.onlinemusic.model.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoveMusicMapper {

    /**
     * 查询喜欢的音乐
     * @return
     */
    Music findLoveMusic(int userId, int musicId);

    /**
     * 收藏音乐
     * @return
     */
    boolean insertLoveMusic(int userId, int musicId);

    /**
     * 查询这个用户，收藏过所有的音乐
     * @return
     */
    List<Music> findLoveMusicByUserId(int userId);

    /**
     * 查询当前用户，指定为 musicName 的音乐，支持模糊查询
     * @return
     */
    List<Music> findLoveMusicByKeyAndUID(String musicName, int userId);

    /**
     * 移除当前用户喜欢的音乐
     * @return
     */
    int deleteLoveMusic(int userId, int musicId);

    /**
     * 根据音乐的 ID 删除喜欢的音乐，就是数据库当中的音乐删除之后，这里的音乐也要删除
     * @param musicId
     * @return
     */
    int deleteLoveMusicByMusicId(int musicId);
}
