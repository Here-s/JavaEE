import com.Bean.BeanScope1;
import com.Bean.BeanScope2;
import com.Bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    /**
     * Bean 的作用域和生命周期
     */
    //Bean 默认情况下是单例模式，Bean 的作用域指的就是 Bean 在 Spring 整个框架当中的某种行为模式
    //  比如 singleton 单例作用域，就表示 Bean 在整个 Spring 当中只有一份，它是全局共享的。
    //  那么当其他人修改了这个值之后，那么另一个人读取到的就是被修改的值。

    //Bean 的作用域：Bean 在 Spring 整个框架中的某种行为（单例模式）
    // 1、singleton：单例模式（默认），通常无状态的 Bean 使用
    // 2、prototype：原型模式（相当于是多例模式，每个人拿到的都是原型），通常有状态的 Bean 使用
    //    就是可能被其他人修改使用
    // 3、request：请求作用域（Spring MVC），每次请求都会创建新的 Bean 实例。
    //    一次 http 的请求和响应的共享 Bean
    // 4、session：会话作用域（Spring MVC），用户会话的共享 Bean。
    // 5、application：全局作用域（Spring MVC），全部人都使用 Bean。Web 应用的上下文信息。
    // 6、websocket：HTTP WebSocket 作用域（Spring WebSocket）。就是 webSocket 的每次会话中，
    //   保存了一个 Map 结构的头信息，原来包裹客户端消息头，第一次初始化之后，
    //   直到 WebSocket 结束都是同一个 Bean

    //单例作用域（singleton） 和 全局作用域（application）
    // singleton 是 Spring Core 的作用域，application 是 Spring Web 中的作用域
    // singleton 作用域 IoC 容器，而 application 作用于 Servlet 容器。

    //设置 Bean 作用域
    // 1、直接设置值：@Scope("prototype")
    // 2、使用枚举设置：@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        BeanScope1 beanScope1 = context.getBean(BeanScope1.class);
        User user1 = beanScope1.getUser();
        System.out.println("BeanScope1：" + user1);

        BeanScope2 beanScope2 = context.getBean(BeanScope2.class);
        User user2 = beanScope2.getUser();
        System.out.println("BeanScope2: " + user2);
    }

    //Bean 的生命周期：
    //1、实例化 Bean（为 Bean 分配内存空间）
    //2、设置属性（对象注入）
    //3、初始化
    // a）执行各种通知（执行各种 Aware）
    // b）执行初始化的前置方法。要重写才会执行这里的前置方法
    // c）执行构造方法，两种执行方式，一种是执行 @PostConstruct，另一种实质性：init-method
    //    init-method 就是经典的 id、class、init-method
    // d）执行初始化的后置方法
    //4、使用 Bean
    //5、销毁 Bean。通过 a）@PreDestroy、b）重写 DisposableBean 接口方法、c）destroy-method
    

}
