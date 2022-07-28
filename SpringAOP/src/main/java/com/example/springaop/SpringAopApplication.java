package com.example.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) {
        //AOP 就是面向切面编程，也是一种思想，
        // 可以实现统一的日志处理，方法执行时间统计
        // 是对某一类事情集中处理。可以做统一的返回格式，统一的异常处理
        // 还可以实现 事务的开启和提交

        //AOP 由切面、连接点、切点、通知 组成
        // 切面：定义 AOP 是针对哪个统一的功能的，这个功能就叫做一个切面，比如用户登录功能或方法的统计日志
        //     就各自是一个切面，切面是由 切点 和 通知 组成的。 所有的方法都可以看作是切面
        // 连接点：所有可能触发 AOP（拦截方法的点）就称之为连接点
        // 切点：提供一组规则，来完成 切面和连接点 的匹配。也就是定义 AOP 拦截规则。
        // 通知：规定 AOP 执行的时机和执行的方法
        //   前置通知：@Before：通知方法会在目标方法调用之前执行
        //   后置通知：@After：通知方法会在目标方法返回或者抛出异常后调用
        //   返回之后通知：@AfterReturning：通知方法会在目标方法返回后调用
        //   抛异常后通知：@AfterThrowing：通知方法会在目标方法抛出异常后调用
        //   环绕通知：@Around：通知包裹了被通知方法，在被通知的方法通知之前和通知之后执行自定义的行为

        //Spring AOP 实现：
        // 1、在项目中添加 Spring AOP 框架
        // 2、定义切面
        // 3、定义切点
        // 4、实现通知

        //Spring AOP 动态代理实现
        // 1、JDK Proxy（JDK 动态代理实现）
        // 2、CGLIB Proxy  默认情况下。 Spring AOP 都会采用 CGLIB 来实现动态代理
        //   原理是通过继承代理对象来实现动态代理的（子类也有父类的所有功能）
        //   不过不能代理最终类（就是被 final 修饰的类
        //区别：一个是 JDK 提供的，一个是第三方提供的，JDK 慢一点

        //代理的生成时期：
        // 编译器：切面在目标类编译时被织入，
        // 类加载期：切面在模板类加载到 JVM 时被织入
        // 运行期：切面在应用运行的某一时刻被织入
        SpringApplication.run(SpringAopApplication.class, args);
    }

}
