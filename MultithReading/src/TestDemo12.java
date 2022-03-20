public class TestDemo12 {

    //线程等待：多个线程之间，调度顺序不确定。线程之间的执行是按照调度器来安排的，这个过程可以是：无序，随机
    //线程等待，就是其中一种，控制线程执行顺序的手段，主要是控制线程结束的先后顺序

    //join 调用 join 的时候，哪个线程调用 join 哪个线程就会阻塞等待，得到对应的线程执行完毕为止（对应的 run 执行完）
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
    }
}
