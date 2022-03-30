import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test6 {

    //线程池
    //因为一个进程比较重，频繁的创建和销毁，开销就会大，解决方法：进程池 or 线程
    //线程：虽然比进程轻了，但是如果创建和销毁的频率进一步增加，发现开销还是有的，解决方案：线程池 or 协程
    //线程池：把线程提前创建好，放到池子里，需要的话，就从池子里取。不用的话，就放回池子里，下次备用。

    //用户态 vs 内核态  一般认为：纯用户态的操作效率比经过内核态处理的操作，要效率更高。
    //认为内核态效率低，并不是说就真的低，而是代码进入了内核态，就不可控了。
    //创建线程就需要内核的支持

    //Java 标准库当中，线程池的使用。ThreadPoolExecutor 不过用起来有点麻烦
    //concurrent 并发的意思。缩写是 juc

    //常考面试题（实际开发也用到）
    //有一个程序，这个程序要并发的/多线程的来完成一些任务，如果使用线程池的话，这里的线程数设为多少合适？
    //指定线程池的个数的时候，不能直接确定线程数，要通过性能测试的方法找到合适的值。

    //标准库当中，还有简化版本的线程池：Executors 本质是针对 ThreadPoolExecutor 进行封装
    public static void main(String[] args) {
        //创建固定线程数目的线程池，参数指定了线程个数
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        //创建一个自动扩容的线程池，会根据任务量来自动进行扩容
//        Executors.newCachedThreadPool();
//        //创建只有一个线程的线程池
//        Executors.newSingleThreadExecutor();
//        //创建一个带有定时器功能的线程池
//        Executors.newScheduledThreadPool();

        for (int i = 0; i < 100; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello threadpool");
                }
            });
        }
    }

    //线程池里面有啥：
    // 1、先能够描述任务（直接 Runnable）
    // 2、需要组织任务（直接用 BlockingQueue）
    // 3、能够描述工作流程
    // 4、还需要组织这些线程。
    // 5、需要实现，往线程池里添加任务
}
