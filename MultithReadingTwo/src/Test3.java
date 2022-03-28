import java.util.Scanner;

public class Test3 {

    //内存可见性导致的线程安全问题。内存可见性是编译器优化中的一个典型案例。
    //编译器优化就是一个玄学问题，对于普通程序员来说，啥时候优化，啥时候不优化。很难说。
    //加上 volatile 就可以避免内存可见性了。
    private static volatile int isQuit = 0;
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while (isQuit == 0) {

            }
            System.out.println("循环结束，t 线程退出");
        });
        t.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个 isQuit 的值：");
        isQuit = scanner.nextInt();
        //输入之后，t 线程并没有退出，就是因为内存可见性。为了提升效率，就直接去寄存器读内容了。

        //解决方案：
        // 1、使用 synchronized 关键字加锁。不光保证原子性，还能保证内存可见性
        // 2、使用 volatile 关键字，volatile 和原子性无关，但是能保证内存可见性。
        //   就可以避免直接去读内存，产生上述问题。编译器每次判断的时候，都会重新从内存当中读取信息
        System.out.println("main 线程执行完毕");
    }
}
