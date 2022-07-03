import com.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Test {

    //通过 BeanFactory 来获取对象
    public static void main2(String[] args) {
        //得到 bean 工厂
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        //获取 bean
        User user = (User) factory.getBean("user");
        //使用 bean
        user.sayHi("李四");
    }
    //使用 ApplicationContext 获取到 对象
    public static void main(String[] args) {
        //1、得到 spring 上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2、根据上下文对象提供的方法获取到 bean。参数填的是 xml 当中的 Bean 的 id。
        // 对象是 Bean 里面 class 对应的对象。

        //getBean 方法重载的多种用法：

        //使用 bean name 获取 bean
//        User user = (User) context.getBean("user");

        //根据 bean type 获取 bean。这种写法简单，但容易出问题，当同一个类型被注入 spring
        // 多次（多个）的时候，就会报错
//        User user = context.getBean(User.class);//这种写法简单，但容易出问题，

        //根据 bean name 和类型获取 bean
        User user = context.getBean("user", User.class);
        //使用
        user.sayHi("张三");
    }

    //ApplicationContext 和 BeanFactory 的区别：
    // 相同点：都可以实现从容器中获取 bean，都提供了 getBean 方法
    // 不同点：
    // 1、ApplicationContext 属于 BeanFactory 的子类。BeanFactory 只提供了
    //  基础访问 Bean 的方法，而 ApplicationContext 除了拥有 BeanFactory 的所有
    //  功能之外，还提供了更多的实现方法。比如对国际化的支持，资源访问的支持，
    //  以及事件和传播等方面的支持。
    // 2、从性能方面来说，二者是不同，BeanFactory 是按需加载(懒加载) Bean。
    //  ApplicationContext 是饿汉模式，在创建时会将所有的 Bean 都加载起来，已备以后使用。






    //Spring 是包含了众多工具方法的 IoC 容器
    // IoC：Inversion of Control 就是控制反转。就是实现方式调转了
    // 构建⼀辆⻋，然⽽⻋需要依赖⻋身，⽽⻋身需要依赖底盘，⽽底盘需要依赖轮胎
    // 这样就导致有强烈的耦合性。耦合性强的话，一个部分改变，就会导致很多内容改变
    // 所以就需要程序尽量解耦合，就是秀嘎底层的时候，上层的内容不会被影响改变，
    // IoC 就是把控制权交出去，用的时候找容器用就好了。就是从底层开始创建，
    // 需要什么就从构造方法里面添加就好了，就不用修改一整个程序的调用练了。

    //IoC 的优点：实现代码的解耦。  对象（Bean）生命周期交给 IoC 框架来维护，我们就不用关注了
    // 就是对象的创建和销毁都交给 Spring 来管理了。
    //Spring IoC 核心功能：
    // 1、将 Bean(对象) 存储在 Spring 容器当中。
    //  a）先在 spring 项目中添加配置文件。在 resources 中新建一个 xml 文件
    //  b）创建一个 Bean 对象，就是一个普通的 Java 对象
    //  c）在配置文件中将需要保存到 spring 中的 bean 对象进行注册。将 bean 通过配置文件
    //   注册到 spring 中。
    // 2、将 Bean(对象) 从 Spring 中取出来。
    //  a）先得到 spring 上下文对象
    //  b）再通过上下文对象提供的方法获取需要使用的 bean 对象。
    //  c）使用 bean 对象（可选）

    //Spring 中的 DI，就是 Dependency Injection，就是 依赖注入 。就是由 IoC 运行的时候
    // 动态的将某种依赖关系注入到对象之中，所以，DI 和 IoC 是从不同角度描述同一件事，就是通过
    // 引入 IoC 容器，利用依赖关系的注入的方式，实现对象之间的解耦。

    //IoC 和 DI 的区别是什么
    // 1、IoC 是一种思想，而 DI 是一种实现手段。就像去吃好吃的，就是 IoC，去吃的好吃的是火锅，就是 DI。
}
