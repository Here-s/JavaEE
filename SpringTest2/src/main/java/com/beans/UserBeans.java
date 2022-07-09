package com.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserBeans {

    //只使用一个 Bean 注解，是没办法把对象存储到容器中的，还需要一个 Component
    @Bean(name = {"userInfo","user1"}) //多个名字就用 大括号 括起来，一个名字就不用大括号。
    //Bean 命名规则，当没有设置 name 属性时，那么 Bean 默认的名称就是方法名，设置了 name 属性之后
    // 方法名就访问不到对象了，只能用重命名的名字。
    public User user1() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
    //对象注入（对象装配）：就是从 Spring 当中把对象读出来。
    // 1、属性注入（字段注入），通过 @Autowired 注解来注入
    // 2、构造方法注入
    // 3、Setter 注入

    //属性注入和构造方法注入，及 Setter 注入的区别是啥？（面试常考）
    // 1、属性注入写法简单，但是通用性不好，只能运行在 IOC 容器下，如果是 非IOC 容器
    //    就会出现问题。
    // 2、Setter 注入：早期 Spring 推荐的方法，Setter 注入通用性没有构造方法注入通用。
    // 3、构造方法注入：通用性更好，可以确保在使用注入对象之间，此注入对象一定初始化过了。
    //    当构造方法注入参数过多时，此时开发者就要检查自己所写的代码是否符合单一设计原则的规范了，
    //    此注入方式也是 spring 后期版本中推荐的注入方式。

    //当有两个 User 对象的时候，就不能把对象注入到其它对象了。这里只有一个，所以没事。
    //Bean 注解，将一个类型的对象，注入多次的问题。解决方法：
    // 1、精确的描述 Bean 的名称（写清楚每个对象的具体名称）
    // 2、使用 @Resource 设置 name 的方式来重命名注入对象。
    // 3、使 用 @Autowired + @Qualifier 来筛选 Bean 对象。

    //@Autowired 和 @Resource 的区别
    // 1、都可以将一个对象注入到对象当中
    // 2、出生不同：@Resource 来自于 JDK  @Autowired 来自于 Spring 框架
    // 3、用法不同：@Autowired 支持属性注入、构造方法注入 和 Setter 注入。
    //   而 @Resource 不支持构造方法注入
    // 4、支持的参数不同：@Resource 支持更多的参数设置，比如 name、type 设置，
    //   而 @Autowired 只支持 required 参数设置。
    // 不过在同一个类当中，可以同时使用这俩进行类注入。


    public void sayHi() {
        System.out.println(" hello Bean");
    }
}
