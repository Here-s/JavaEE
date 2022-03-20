class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hello");
    }
}

public class TestDemo3 {

    //创建线程的第二种写法。通过 Runnable 接口
    //通过 Runnable 来描述 任务的内容，进一步的把描述好的任务交给 Thread 实例
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    //写法 三 和 四 ，就是上面两种写法的翻版，使用了匿名内部类。
}
