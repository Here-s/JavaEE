package com.example.springboot1.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "dbtypes")//读取配置文件中的集合
public class ReadList {
    private List<String> name;
}
