class MyThread2 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Thread!");

            try {
                //强制休息 一秒钟，就是让线程强制进入阻塞状态，单位是 ms
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestDemo2 {

    public static void main(String[] args) {
        MyThread2 t = new MyThread2();
        t.start();
        //一个进程中，至少会有一个线程，在一个 Java 进程中，至少会有一个调用 main 方法的线程，
        // 自己创建的 t 线程，和自动创建的 main 线程，就是并发执行的关系（宏观上看起来是同时执行）

        //打印一个，休眠一秒，时间到了之后先唤醒 main 还是 Thread 这是不确定的（随机的，也叫抢占式执行）
        //操作系统来说，内部对于线程之间的调度顺序，在宏观是可以认为是 随机的。

        //协程的调度是程序员手动进行的，不依赖系统。
        while (true) {
            System.out.println("Main");

            try {
                //强制休息 一秒钟，就是让线程强制进入阻塞状态，单位是 ms，这里并不是很精确的 1000 ms
                // 这里的时间会有一些小的误差
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
