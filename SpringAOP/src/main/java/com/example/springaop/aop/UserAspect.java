package com.example.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class UserAspect {
    //定义切点 使用 Aspect 表达式语法 （execution（<修饰符><返回类型><包.类.方法(参数）><异常>））,
    //Aspect 语法中的通配符
    // 1、*：表示匹配任意内容，用在返回值，包名，类名，方法名都可以使用
    // 2、..：匹配任意字符，可以使用在方法参数上，如果用在类上，需要配合 * 一起使用
    // 3、+：表示匹配指定类及其底下的所有子类，比如 com.User+ 表示匹配 User 及其所有的子类
    @Pointcut("execution(* com.example.springaop.controller.UserController.*(..))")
    public void pointcut(){}

    /**
     * 定义 pointcut 切点的前置通知，就是执行方法之前执行的方法
     */
    @Before("pointcut()")
    public void doBefore() {
        System.out.println("执行前置通知");
    }

    /**
     * pointcut 的后置通知
     */
    @After("pointcut()")
    public void doAfter() {
        System.out.println("执行后置通知");
    }

    /**
     * AfterReturning 在 return 之前执行，执行完之后才执行后置通知
     */
    @AfterReturning("pointcut()")
    public void doAfterReturning() {
        System.out.println("AfterReturning  返回之后通知");
    }

    /**
     * 抛出异常之后通知
     */
    @AfterThrowing("pointcut()")
    public void doAfterThrowing() {
        System.out.println("doAfterThrowing 抛出异常后通知");
    }

    /**
     * 环绕通知，并且实现每个方法的执行时间
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object result = null;
        //spring 当中的时间统计对象
        StopWatch stopWatch = new StopWatch();
        System.out.println("Around 前置通知");
        try {
            //统计方法的执行时间，开始计时
            stopWatch.start();
            //执行目标方法，以及目标方法所对应的相应的通知
            result = joinPoint.proceed();
            //统计方法的执行时间，停止计时
            stopWatch.stop();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("Around 后置通知");
        System.out.println(joinPoint.getSignature().getName() + " 方法时间：" + stopWatch.getTotalTimeMillis() + "ms");
        return result;
    }
}
