public class TestDemo10 {

    //中断线程：让一个线程停下来。线程停下来的关键：要让线程对应的 run 方法执行完。
    //特殊的 main 线程，对于 main 来说，要等到 main 执行完，才会停下来。
    private static boolean isQuit = false;
    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            //在其它线程当中去控制标志位，就能影响到线程的结束。
            //此处因为多个线程共用一个虚拟地址空间，因此 main 线程修改的 isQuit 和 t 判断的 isQuit 是同一个值
            //这样的代码并不严谨，可以通过 Thread 自带的标志位来判定。：Thread.interrupted()
            //  Thread.currentThread().isInterrupted()
            while (!isQuit) {
                System.out.println("Thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        //只要把 isQuit 设置为 true，此时循环退出，然后就是 run 完了，再然后就是线程结束

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isQuit = true;
        System.out.println("终止线程");
    }
}
