package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.ArticleInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ArticleMapperTest {

    @Resource
    private ArticleMapper articleMapper;

    @Test
    void getArticleById() {
        ArticleInfo articleInfo = articleMapper.getArticleById(1);
        log.info("文章详情" + articleInfo);
    }
}