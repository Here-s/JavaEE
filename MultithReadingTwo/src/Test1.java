public class Test1 {


    //进程有状态：就绪和阻塞，这些状态决定了系统按照啥样的态度来调度这个进程


    //一些关键线程阻塞，就会出现卡死的情况。分析卡死原因的时候，第一步先看看线程所处的状态
    // 看了状态之后分析程序出现问题的原因。
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
        t.getState();
    }
}
