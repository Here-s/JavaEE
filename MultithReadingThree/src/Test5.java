import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

//创建一个类，表示一个任务
class MyTask implements Comparable<MyTask> {
    //任务具体要干什么
    private Runnable runnable;
    //任务具体啥时候干，保存任务要执行的毫秒级时间戳
    private long time;
    public long getTime() {
        return time;
    }

    //after 是一个时间间隔，不是绝对的时间戳的值
    public MyTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    public void run() {
        runnable.run();
    }


    @Override
    public int compareTo(MyTask o) {
        return (int) (this.time - o.time);
    }
}
class MyTimer {
    //定时器内可以存放很多任务，要考虑到多线程问题，还要注意到线程安全。
    private PriorityBlockingQueue<MyTask> queue = new PriorityBlockingQueue<>();
    private Object locker = new Object();

    public void schedule(Runnable runnable, long delay) {
        MyTask task = new MyTask(runnable,delay);
        queue.put(task);
        //每次任务插入成功之后，都唤醒一下扫描线程，重新检查一下队首的任务时间是否到了
        synchronized (locker) {
            locker.notify();
        }
    }

    public MyTimer() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    MyTask task = queue.take();
                    long curTime = System.currentTimeMillis();
                    //这里应该实现比较方法，因为默认的比较不能用。
                    //标准库当中的集合类，很多都是有一定的约束限制的，不是随便拿个类都能放到集合类里面去的。
                    if (curTime < task.getTime()) {
                        //说明时间没到
                        queue.put(task);
                        //指定一个等待时间，时间到了之后，等待自然也就唤醒了。
                        // sleep 不能被中途唤醒， wait 是可以被中途唤醒的。
                        synchronized (locker) {
                            locker.wait(task.getTime() - curTime);
                        }
                    } else {
                        //时间到了
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
//写上述代码的时候有两个非常需要注意的地方：
// 1、要实现 MyTask 的比较
// 2、给循环加限制。如果队列中的任务是空着的，就还好，这个线程就阻塞了。
//  就怕队列不为空，并且任务时间还没到，就会一直看任务，浪费资源，也就是忙等。忙等是很浪费 CPU 的
//  避免忙等：通过设计查询比率，可以通过 wait 这样的机制来实现。
//  wait 有一个版本，指定等待时间（不需要 notify，时间到了自然唤醒）就不会忙等了。
public class Test5 {

    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello Timer");
            }
        },3000);
        System.out.println("main");
    }

    //通过 Timer 的 schedule 任务来设计任务计划，Timer 内部是有专门的线程，
    // 来负责执行注册的任务的，所以执行完之后，并不会马上退出线程。
    //管理任务：
    // 1、描述任务：创建一个专门的类来表示一个定时器中的任务（Timer Task）
    // 2、组织任务：通过一定的结构来组织。任务是无序的，但是执行的时候是有序的。
    //  要快速找到所有任务当中，时间最小的任务。通过堆来解决这样的问题。
    // 3、执行时间到了的任务。执行时间最先到了的任务，需要有一个线程，
    // 不停的去检查当前优先级队列的队首元素，看看最靠前的任务是不是时间以及到了。
}
