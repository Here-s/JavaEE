import com.beans.UserController2;
import com.beans.UserController3;
import com.beans.UserController4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.Introspector;

public class Test2 {

    //测试类注入
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        UserController2 userController2 = context.getBean(UserController2.class);
//        userController2.sayHi();

//        UserController3 userController3 = context.getBean(UserController3.class);
//        userController3.sayHi();

        UserController4 userController4 = context.getBean(UserController4.class);
        userController4.sayHi();
    }
}
