package com.Bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UserBeans {

    @Bean(name = "user")
//    @Scope("prototype")//加了 Scope 之后，被别人修改的话，其他人拿到的也还是这里的值
    //除了使用 @Scope("prototype") 还可以使用 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
}
