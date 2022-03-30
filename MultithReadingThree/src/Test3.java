import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Test3 {

    //阻塞队列：相对于普通队列，又有了一些其他方面的功能：
    // 1、线程安全
    // 2、产生阻塞效果
    //   1）如果队列为空，尝试出队列，就会出现阻塞，阻塞到队列不为空为止。
    //   2）如果队列为慢，尝试入队列，就会出现阻塞，阻塞到队列不为满为止。
    //基于阻塞队列的特性，就可以实现“生产者消费者模型” 日常开发当中，处理多线程的一个经典方式。
    // “生产者消费者模型” 非常有用，尤其是服务器开发当中。可以极大程度上降低耦合。
    // 就是在两个服务器之间通过 阻塞队列 来连接。这样的话，服务器挂了之后，对另外一个服务器也不影响。
    //阻塞队列优点2：能够对于请求进行“削峰填谷。可以防止请求暴涨，导致效果不可控。
    //实际开发当中：阻塞队列不是一个简单的数据结构了，而是一个/一组专门的服务器程序，提供的功能不仅仅是队列阻塞。
    // 还会在这些基础上面提供更多的功能（数据持久化存储，多个数据通道，多节点备份，支持控制面板，方便配置参数
    //又叫”消息队列“

    //Java 标准库当中的阻塞队列的用法，基于内置的阻塞队列，实现”生产者消费者模型“。
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        //入队
        queue.put("hello");
        //出队
        String s = queue.take();
        //也有 offer  poll  peek 但是这些没有阻塞功能。
    }
}
