public class TestDemo8 {

    //Java 线程一启动，不仅仅是代码当中的线程，还有其它的一些线程（JVM 自己创建的）
    //如果线程是后台线程，就不影响线程退出。结束 main 线程，就把后台的线程也全部结束了。
    // 如果是前台线程，就会影响线程退出。得等到线程结束，才能结束 main 线程
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            while (true) {
                System.out.println("Thread t1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread t1");
        t1.start();

        Thread t2 = new Thread(()-> {
            while (true) {
                System.out.println("Thread t2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread t2");
        t2.start();
    }
}
