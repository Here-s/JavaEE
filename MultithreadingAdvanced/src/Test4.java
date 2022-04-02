import java.util.concurrent.CountDownLatch;

public class Test4 {
    //CopyOnWriteArrayList 写时拷贝，修改的时候，会创建一份副本出来。
    // 这样做的时候，就是修改的同时对于读操作，是没有任何影响的。读的时候先读旧的版本。
    // 就不会出现：读到了一个“修改了一半”的中间状态。
    // 也叫做：双缓冲区 策略。更新配置的时候用到这个。

    //CountDownLatch 就是像一个 终点线 的东西。有不同的东西去终点
    // 就像下载文件，把文件拆分为多个文件，通过多线程下载就可以更快，所有线程都跑完，就下载完了。
    //CountDownLatch 的 countDown 方法，给每个线程里面去调用，就表示到达终点了。
    // await 是给等待线程去调用，当所有的任务都要到达终点了，await 就从阻塞中返回，就表示任务完成。
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()-> {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "到达终点！");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        //裁判就要等待所有的线程到达
        latch.await();
    }

}
