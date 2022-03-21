public class TestDemo13 {


    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取到线程名字
                //对于这个代码来说：是通过 Thread 的方式来创建线程。此时在 run 方法当中，
                // 直接通过 this 拿到的就是 Thread 的实例
                System.out.println(Thread.currentThread().getName());
            }
        });
        t.start();
    }

    //这里会报错，是因为这里的 this 指向的不是 Thread 类型了，而是指向 Runnable。
    //而 Runnable 只是一个单纯的任务，没有 name 属性。
    //要拿到线程的名字，只能通过 Thread.currentThread() 来获取,lambda 表达式当中也是这样
    public static void main2(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取到线程名字
                //对于这个代码来说：是通过 Thread 的方式来创建线程。此时在 run 方法当中，
                // 直接通过 this 拿到的就是 Thread 的实例

//                System.out.println(This.getName());
            }
        });
        t.start();
    }

    //Thread.currentThread() 获取当前线程的 引用（Thread 实例的引用）
    public static void main1(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                //获取到线程名字
                //对于这个代码来说：是通过 Thread 的方式来创建线程。此时在 run 方法当中，
                // 直接通过 this 拿到的就是 Thread 的实例
                System.out.println(Thread.currentThread().getName());
            }
        };
        t.start();

        //因为是在 main 线程中调用的，所以拿到的就是 main 线程的实例。
        System.out.println(Thread.currentThread().getName());
    }
}
