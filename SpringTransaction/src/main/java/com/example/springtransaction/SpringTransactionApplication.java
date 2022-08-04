package com.example.springtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTransactionApplication {

    public static void main(String[] args) {
        //spring 中编程事务的实现

        //事务传播机制：多个事务在相互调用时，事务是传递是，事务1、事务2、事务3.
        // 事务传播机制保证⼀个事务在多个调⽤⽅法间的可控性的（稳定性的）
        // 1、Propagation.REQUIRED：默认的事务传播级别，它表示如果当前存在事务，则加⼊该事务
        //  如果当前没有事务，则创建⼀个新的事务。
        // 2、Propagation.SUPPORTS：如果当前存在事务，则加⼊该事务；如果当前没有事务，
        //  则以⾮事务的⽅式继续运⾏。
        // 3、Propagation.MANDATORY：（mandatory：强制性）如果当前存在事务，则加⼊该事务
        //  如果当前没有事务，则抛出异常。
        // 4、Propagation.REQUIRES_NEW：表示创建⼀个新的事务，如果当前存在事务，则把当前事务挂起。
        //  也就是说不管外部⽅法是否开启事务，Propagation.REQUIRES_NEW 修饰的内部⽅法会新开启⾃⼰的事务，
        //  且开启的事务相互独⽴，互不⼲扰。
        // 5、Propagation.NOT_SUPPORTED：以⾮事务⽅式运⾏，如果当前存在事务，则把当前事务挂起。
        // 6、Propagation.NEVER：以⾮事务⽅式运⾏，如果当前存在事务，则抛出异常。
        // 7、Propagation.NESTED：如果当前存在事务，则创建⼀个事务作为当前事务的嵌套事务来运⾏
        //  如果当前没有事务，则该取值等价于 PROPAGATION_REQUIRED。
        SpringApplication.run(SpringTransactionApplication.class, args);
    }

}
