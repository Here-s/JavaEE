package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    //根据文章 id 获取文章。一对一查询
    public ArticleInfo getArticleById (@Param("id") Integer id);
}
