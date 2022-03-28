public class Test8 {

    //volatile 只保证内存可见性，不保证原子性。
    //volatile 禁止编译器优化，保证内存可见性。
    //  计算机想要之一些计算，就需要把内存当中的数据读到 CPU 寄存器中，然后再在寄存器中计算，
    //  再写回到内存中。CPU 访问寄存器的速度，比访问内存快太多了，当 CPU 连续多次访问内存，发现结果都一样，
    //  CPU 就会偷懒，然后直接去读寄存器，从而导致结果出现错误

    //面试有时候会问 volatile 和 synchronized 的区别：这俩本来没关系，恰好都是 Java 的关键字。

    //不能无脑用 synchronized ，容易导致线程阻塞，一旦线程阻塞（放弃CPU），下次回到 CPU，
    // 这个时间就不可控了，如果调度不回来，自然对任意的任务执行时间就拖慢了。
    //一旦代码当中使用了 synchronized ，这个代码大概率就和 高性能 无缘了。
    //volatile 就不会引起线程阻塞

    //wait 和 notify  等待和通知。是处理线程调度随机性的问题的，不喜欢随机性，需要让彼此之间有个固定的顺序
    // join 也是一种控制顺序的方式，更倾向于控制线程结束。
    // 这俩都是 Object 对象的方法。
    // 调用 wait 方法就会陷入阻塞。阻塞到有其他线程通过 notify 来通知。

    public static void main1(String[] args) throws InterruptedException {
        Object object = new Object();
        System.out.println("wait 前");
        //代码当中调用到 wait，就会发生阻塞。会抛出异常
        //wait 内部会做三件事：1、先释放锁 2、等待其他线程的通知 3、收到通知之后，重新获取锁，并继续往下执行
        //因此，想用 wait/notify 就得搭配 synchronized
        object.wait();
        System.out.println("wait 后");
    }

    //搭配 synchronized 来使用
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        //wait 哪个对象，就得针对哪个对象加锁。
        synchronized (object) {
            System.out.println("wait 前");
            object.wait();
            System.out.println("wait 后");
        }
    }
}
