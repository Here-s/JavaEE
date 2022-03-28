public class Test1 {



    //进程有状态：就绪和阻塞，这些状态决定了系统按照啥样的态度来调度这个进程
    // 相当于是针对一个进程当值只有一个线程的情况。
    //更常见的是：一个进程当中包含了多个线程，所谓的状态，其实是绑定在线程上
    //Linux 当中的 PCB 其实是和 线程相对应的，一个进程对应着一组 PCB。
    //就绪和阻塞都是针对 系统层面 上的线程的状态（PCB）

    //在 Java 中的 Thread 类中，对于线程的状态，又进一步细化了。细化之后方便查看问题出在哪里
    // NEW: 安排了工作, 还未开始行动。就是把 Thread 对象创建好了，
    //  但是还没有调用 start，也就是还没有创建线程

    // TERMINATED: 工作完成了.操作系统当中的线程以及执行完毕销毁了，但是 Thread 对象还在
    //上面这两种状态，是 Java 内部搞出来的状态，就和操作系统中的 PCB 里的状态就没啥关系。

    // RUNNABLE: 可工作的. 又可以分成正在工作中和即将开始工作。
    //  就是就绪状态，处于这个状态的线程，就是在就绪队列中，随时可以被调度到 CPU 上。
    //  如果代码当中没有进行 sleep 和其它可能导致阻塞的操作。代码大概率是处于 RUNNABLE 状态的

    // TIMED_WAITING: 这几个都表示排队等着其他事情。代码当中调用了 sleep 就会进入 TIMED_WAITING
    //  或者 join 后面加上 超时时间，也会进入 TIMED_WAITING 状态，就是当前线程在一段时间之内，是阻塞状态

    // BLOCKED: 这几个都表示排队等着其他事情。表示当前线程在等待锁，导致了阻塞（阻塞状态之一）
    //  加锁的时候 synchronized
    // WAITING: 这几个都表示排队等着其他事情。当前状态在等待唤醒，导致了阻塞（阻塞状态之一）
    //  wait 方法等待的时候

    //线程运行状态流程：NEW -> start -> RUNNABLE -> run 方法执行完 -> TERMINATED
    //在 RUNNABLE 的时候可以通过 sleep 来达到 TIMED_WAITING 或者通过 加锁 来达到 BLOCKED
    //或者通过 wait 来达到 WAITING 效果

    //一些关键线程阻塞，就会出现卡死的情况。分析卡死原因的时候，第一步先看看线程所处的状态
    // 看了状态之后分析程序出现问题的原因。
    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //通过这个方法获得指定线程的状态
        System.out.println(t.getState());
    }

    //多线程
    //线程是一个独立的执行流：解决并发编程这样的问题（进程也可以解决并发编程，但是消耗更大）
    //线程比进程更轻量
    //Thread 类，Java 中对于系统提供的多线程 api 进行的封装

    //构造方法：创建线程
    //五种写法：1、继承 Thread ，2、实现 Runnable ，3、继承 Thread（匿名内部类），
    // 4、实现 Runnable（匿名内部类），5、lambda 表达式
    //构造方法当中还能指定线程的名字。

    //创建线程（系统中创建） 通过 start方法
    //中断线程：1、核心思想：让 run 方法执行完。
    // 2、使用 Thread 内部类的标志位来完成中断。或者通过抛出异常（线程处于阻塞状态）

    //等待线程：join 一个线程等待另外一个线程结束，会导致线程处于阻塞状态。
    // 可以设置一个等待时间，如果等待时间内程序没响应的话，就关掉程序。

    //获取线程引用：通过 currentThread 方法，哪个线程调用这个方法，就获取到哪个线程的 Thread 实例

    //休眠线程：通过 sleep 来让线程处于阻塞状态。
}
