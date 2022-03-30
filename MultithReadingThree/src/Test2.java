//实现单例模式，懒汉模式
class Singleton2 {
    //1、不是立即初始化实例
    private static volatile Singleton2 instance = null;
    //2、把构造方法设为 private
    private Singleton2() {}
    //3、提供一个方法来获取上述单例的实例
    //  当真正用到这个实例的时候，再去创建实例。就会节省整体的资源。
    public static Singleton2 getInstance() {
        //通过加锁来保证线程安全。如果条件成立，说明是未初始化过的，存在线程安全，就需要加锁。
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    //只有真正在使用到的时候，才会真正创建这个实例
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
public class Test2 {

    //如何保证懒汉模式的线程安全：加锁。给 if 和 赋值 加锁。加锁的时候，可以直接指定 类对象.class 作为锁对象
    //  加锁之后，线程安全问题就得到了解决，但是又有了新的问题。
    //对于上面这个懒汉模式的代码来说，线程不安全，是发生在 instance 被初始化之前的。未初始化的时候，
    // 多线程调用 getInstance 就可能同时涉及到读和修改。但是 instance 一旦被初始化之后，也就只剩读操作了，
    // 线程也就安全了。
    // 上面的加锁方式，无论是都初始化，每次调用 getInstance 都会进行加锁，就算是初始化之后，依然会有大浪的锁竞争
    //加锁会保证线程安全，但是也付出了代价（程序的速度变慢了）。
    // 所以改为：getInstance 初始化之前加锁，初始化后不加锁
    //还有一个问题，如果多个线程，都去读 getInstance 那么就可能导致优化为直接去寄存器读。
    // 如果优化之后，第一个线程已经完成了修改，那么后续的线程就感知不到修改了。会引起第一次条件的误判。
    // 也就是导致不该加锁的给加锁了。那么就给最开始加上 volatile 代码就完整了。
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
    }
}
