package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.Music;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper {
    /**
     * 插入音乐
     * @param title
     * @param singer
     * @param time
     * @param url
     * @param userid
     * @return
     */
    int insert(String title, String singer,
                      String time, String url, int userid);

    /**
     * 查询当前的音乐是否存在
     * @param id
     * @return
     */
    Music findMusicById(int id);

    /**
     * 删除当前 id 的音乐
     * @param musicId
     * @return
     */
    int deleteMusicById(int musicId);
}
