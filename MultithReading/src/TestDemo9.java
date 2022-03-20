public class TestDemo9 {

    //Thread 中的一些重要方法：
    // 1、start
    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            while (true) {
                System.out.println("Thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//        t.start();

        //start 和 run 也是经典面试题

        //run 只是一个 普通的方法，描述了任务的内容，start 是一个特殊的方法，会在系统当中创建线程。
        // main 线程当中调用 run，其实并没有创建新的线程，这个循环仍然是在 main 当中执行的，
        // 也就是 run 运行完，才会运行 main 线程
        t.run();
        while (true) {
            System.out.println("Main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
