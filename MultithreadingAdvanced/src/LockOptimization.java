import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class LockOptimization {

    //synchronized 中的锁优化机制
    //1、锁优化：体现了 synchronized 能够“自适应”这样的能力。
    // 偏向锁：并不是真的加锁，只是做了一个标记。带来的好处就是后续没人竞争的时候，就避免了加锁解锁的开销
    //2、锁粗化：指的是锁的“粒度”。加锁代码的范围越大，认为粒度越粗，范围越小，粒度越细。
    // 锁的粒度比较细，多个线程之间的并发性更高。
    //3、锁消除：有些代码，明明不用加锁，结果给加锁了，编译器就会发现加锁没啥必要，就直接去掉了。

    //Java 当中的 JUC
    //java.util.concurrent 是并发（多线程相关的操作）
    // Callable 是一个 interface 也是一种创建线程的方式。
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i <= 1000; i++) {
                    sum += i;
                }
                return sum;
            }
        };
        //为了让线程执行 callable 中的任务，光使用构造方法还不够，需要一个辅助的类。
        //通过 FutureTask 来作为中转
        FutureTask<Integer> task = new FutureTask<>(callable);
        //创建线程，来完成这里的工作
        Thread t = new Thread(task);
        t.start();
        //相当于运行任务的时候，在阻塞情况，要等到阻塞之后，运行完之后就可以输出了
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
