import java.util.Timer;
import java.util.TimerTask;

public class Test4 {

    //通过 Timer 的 schedule 任务来设计任务计划，Timer 内部是有专门的线程，
    // 来负责执行注册的任务的，所以执行完之后，并不会马上退出线程。
    //管理任务：
    // 1、描述任务：创建一个专门的类来表示一个定时器中的任务（Timer Task）
    // 2、组织任务：通过一定的结构来组织。任务是无序的，但是执行的时候是有序的。
    //  要快速找到所有任务当中，时间最小的任务。通过堆来解决这样的问题。
    // 3、执行时间到了的任务。
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello Timer");
            }
        }, 3000);//就是在 3 秒之后执行这个任务，
        System.out.println("main");
    }
}
