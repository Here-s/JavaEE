public class TestDemo11 {


    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            //这里的标志位，在结束线程之后，并不清理标志位。无脑使用这个就好。
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //发生了异常，就单纯的只是打印了一个日志。打印之后就继续运行了。
                    e.printStackTrace();
                    //加上 break 之后，就直接退出了。

                    //做一些收尾工作
                    System.out.println("收尾工作");
                    break;
                }
            }
        });
        t.start();

        //在主线程当中，调用 interrupt 方法，来中断这个线程
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        t.interrupt();
        //这里来中断线程，不仅仅对标志位操作，还可能触发异常
        // 1、如果线程是处在就绪状态，就是设置线程的标志位为 true
        // 2、如果 t 线程处在阻塞状态(sleep 休眠了) 就会触发一个 interruptException
        //      因为 sleep 阻塞了，所以此时设置标志位就不能起到及时唤醒的状态，就会打断 sleep，
        //      导致线程从阻塞状态被唤醒，所以就没用了。

    }
}
