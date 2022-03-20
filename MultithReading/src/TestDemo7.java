public class TestDemo7 {

    public static void serial() {
        long beg = System.currentTimeMillis();
        long a = 0;
        for (int i = 0; i < 10_0000_0000; i++) {
            a++;
        }
        long b = 0;
        for (int i = 0; i < 10_0000_0000; i++) {
            b++;
        }
        long end = System.currentTimeMillis();
        System.out.print((end-beg)+"ms");
    }

    public static void concurrency() throws InterruptedException {
        long beg = System.currentTimeMillis();
        Thread t1 = new Thread(()->{
            long a = 0;
            for (int i = 0; i < 10_0000_0000; i++) {
                a++;
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            long b = 0;
            for (int i = 0; i < 10_0000_0000; i++) {
                b++;
            }
        });
        t2.start();
        //不能在此处直接记录时间，因为求时间戳的代码是在 main 线程中，main 和 t1，t2 之间是并发执行的关系
        // 此处 t1 和 t2 还没执行完，就开始记录时间了，所以，main 线程等 t1 和 t2 都跑完，再来记录时间

        // join 就是等待线程结束，
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.print((end-beg)+"ms");
    }
    //多线程能提高完成任务的效率。两个整数，分别自增十亿次，用多线程和单线程来比较
    //多线程是为了更好的利用 CPU 多核的资源
    public static void main(String[] args) throws InterruptedException {
        //这里的时间，并不是说线程时间对半分开。创建多线程的时候，也会浪费时间。
        serial();
        System.out.println();
        concurrency();
    }
}
