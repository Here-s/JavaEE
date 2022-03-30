import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

class MyThreadPool {
    //1、描述一个任务，直接使用 Runnable 不需要额外创建类了。
    //2、使用一个数据结构来组织若干个任务
    private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
    //3、描述一个线程，工作线程的功能就是从任务队列中获取任务并执行。
    static class Worker extends Thread {
        //当前线程池当中，有若干个 Worker 线程，这些线程内部，都持有了上述的任务队列
        private BlockingQueue<Runnable> queue = null;

        public Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            //需要能够拿到上面的队列
            while (true) {
                try {
                    //循环的去获取队列中的任务，如果队列为空，就直接阻塞。如果不是空，就去获取里面的内容
                    Runnable runnable = queue.take();
                    //获取到之后，就去执行。
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //4、创建一个数据结构来组织若干个线程
    private List<Thread> workers = new ArrayList<>();

    public MyThreadPool(int n) {
        //在构造方法当中创建若干个线程
        for (int i = 0; i < n; i++) {
            Worker worker = new Worker(queue);
            worker.start();
            workers.add(worker);
        }
    }
    //5、创建一个方法，能够允许程序员放任务到线程池当中
    public void submit(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MyThreadP {

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(10);
        for (int i = 0; i < 100; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello thread");
                }
            });
        }
    }
}
