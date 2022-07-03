import com.beans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    //Spring 更简单的读取和存储对象
    //1、使用 5 大类注解实现。通过这 5 大类注解中的任意一个都可以实现。五大注解可以让代码可读性提高
    // 让程序员能够直观的判断当前类的用途。Component 可以看作是其它 4 个类的注解。
    // @Controller（控制器） 控制层，做前端参数校验
    // @Service（服务） 服务层，做数据的组装和接口调用
    // @Repository（仓库） 数据持久层，DAO 层，和数据库直接进行交互
    // @Configuration（配置） 配置层
    // @Component（组件）
    //2、使用 @Bean 方法注解，将对象更简单的存储到容器
    public static void main(String[] args) {
        //先得到上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        User user = context.getBean("user1", User.class);
//        User user = context.getBean(User.class);
        System.out.println(user);

//        //得到 bean。默认情况下，id 使用的是 小驼峰
//        UserController controller = context.getBean("userController", UserController.class);
//        //使用 Bean
//        controller.sayHi();

//        UserService userService = context.getBean(UserService.class);
//        userService.sayHi();

//        UserRepository userRepository = context.getBean(UserRepository.class);
//        userRepository.sayHi();

//        UserConfiguration userConfiguration = context.getBean(UserConfiguration.class);
//        userConfiguration.sayHi();

        UserComponent userComponent = context.getBean(UserComponent.class);
        userComponent.sayHi();
    }
}
