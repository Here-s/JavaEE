package com.example.springboot2.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Controller 是用来和前端联系的
@ResponseBody //也要加之后，才不会报 500 错误
@Slf4j //替代了之前需要通过 LoggerFactory.getLogger 的操作
public class UserService {
    //通过 lombok 来实现更简单的日志打印

    @RequestMapping("/sayhi2")
    public void sayHi2() {
        log.trace("Lombok trace");
        log.debug("Lombok debug");
        log.info("Lombok info");
        log.warn("Lombok warn");
        log.error("Lombok error");
    }
}
