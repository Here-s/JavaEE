public class TestDemo5 {



    //通常认为 Runnable 写法更好一些，能够做到让线程和线程执行的任务，更好的解耦。写代码注重 高内聚，低耦合
    // Runnable 单纯的只是描述了一个任务，至于这个任务是要通过一个进程来执行，还是线程池来执行，
    //  还是协程来执行，Runnable 并不关心，Runnable 里面的代码也不关心。
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread");
            }
        });
        t.start();
    }
}
