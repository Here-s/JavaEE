public class Test9 {


    //假设有两个线程 线程 t1：a b c d。   线程 t2：e f g h。
    // 假设我们需要让两个线程按照：a->e  b->f  c->g  d->h 顺序执行。就通过 wait 和 notify
    // a wait e notify e。按照这个样子就可以了。

    //例如现在有一个对象 o 有 10 个线程，都调用了 o.wait 此时 10 个线程都是阻塞状态。
    //如果调用了 o.notify 就会把 10 个当中的一个给唤醒（唤醒哪个不确定）
    // 使用 notifyAll 就会把所有的 10 个线程都给唤醒。wait 唤醒之后，就会重新尝试获取到锁（这个过程就会发生竞争）
    // 与其全部唤醒去竞争，还不如 一个一个去唤醒 。
    public static Object locker = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("wait 之前");
                //wait 之后，sleep 3秒，然后再开始 notify
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait 之后");
            }
        });
        t1.start();

        Thread.sleep(3000);

        //就行 notify
        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("notify 之前");
                    locker.notify();
                System.out.println("notify 之后");
            }
        });
        t2.start();
    }
}
