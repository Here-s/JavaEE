public class TestDemo12 {

    //线程等待：多个线程之间，调度顺序不确定。线程之间的执行是按照调度器来安排的，这个过程可以是：无序，随机
    //线程等待，就是其中一种，控制线程执行顺序的手段，主要是控制线程结束的先后顺序

    //join 调用 join 的时候，哪个线程调用 join 哪个线程就会阻塞等待，等到对应线程的 join 执行完毕为止（对应的 run 执行完）
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
        t.start();

        //在 主线程 当中使用一个等待操作，来等待 t 线程执行结束。
        //调用这个方法的线程，是 main 线程。针对 t 这个线程对象调用的。此时就是让 main 等待 t。
        //调用 join 之后，main 线程就会进入阻塞状态（暂时无法在 CPU 上执行）

        //代码执行到 join 这一行，就暂时停下了，不继续往下执行了。
        //等到 t 的 run 方法跑完之后，join 就能继续往下走了，恢复成就绪状态。
        //这是代码当中的先后顺序，就像自增代码，计时操作就是在 计算线程 执行完之后再执行。

        //优先级是系统内部，进行线程调度使用的参考量，咱们在用户代码层面上控制不了（不能完全干预）
        //join 默认情况下，是死等（不见不散）。所以 join 提供了另外一个版本，可以执行等待时间，
        // 最长等多久，等不到就撤了。就是在 join 里面加个时间就好。
        // 如果在 join 时间内 t 线程结束了。此时 join 直接返回。如果 join 时间之后，t 还没结束，
        // 此时就直接返回。日常开发当中，等待经常用到
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
