package com.example.springtransaction.controller;


import com.example.springtransaction.mapper.LogMapper;
import com.example.springtransaction.model.LogInfo;
import com.example.springtransaction.model.UserInfo;
import com.example.springtransaction.service.LogService;
import com.example.springtransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    //使用编程式的事务
    @RequestMapping("/add")
    public int add(UserInfo userInfo) throws InterruptedException {
        //非空校验
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
            || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        //开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        int result = userService.add(userInfo);
        System.out.println("add 受影响的行数：" + result);
        //提交事务/回滚事务
//        transactionManager.rollback(transactionStatus);//回滚事务
        transactionManager.commit(transactionStatus);//提交
        //
        return result;
    }

    /**
     * Transactional 的作用域，也可以加在类上面，这样的话，类里面的 public 方法默认开启事务
     *  只能用在 public 方法上，不然不生效
     * @param userInfo
     * @return
     */
    //使用声明式的事务
    @Transactional(timeout = 1)//进入方法之前，自动开启事务，方法执行完之后，自动提交事务，如果出现异常，自动回滚事务
    @RequestMapping("/add2")
    public int add2(UserInfo userInfo) {
        //非空校验
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        //默认超时时间时间是 1秒，把方法设置为 3秒，就会触发事务的回滚
        int result = 0;
        try {
            result = userService.add(userInfo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("add 受影响的行数：" + result);
        int num = 10/0;//程序出现异常之后，事务就会回滚
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping("/add3")
    public int add3(UserInfo userInfo) throws InterruptedException {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        int userResult = userService.add(userInfo);
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping("/add4")
    public int add4(UserInfo userInfo) throws InterruptedException {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        int userResult = userService.add(userInfo);
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

    //Spring 事务隔离级别：
    //1、DEFAULT：默认事务隔离级别，使用的是连接的数据库的事务隔离级别
    //2、READ_UNCOMMITTED 读未提交
    //3、READ_COMMITTED 读已提交
    //4、REPEATABLE_READ 可重复读
    //5、SERIALIZABLE 串行化

    //当 Spring 中设置了事务隔离级别和连接的数据库（MySQL）事务隔离级别发生冲突的时候，以 Spring 为准
    //Spring 中的事务隔离级别机制的实现是依靠连接数据库支持事务隔离界别为基础
    //当事务遇到 try/catch 的时候，就不会自动回滚了，可以通过代码的方式手动回滚
}
