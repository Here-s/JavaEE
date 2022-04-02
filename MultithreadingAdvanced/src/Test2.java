import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

    //ReentrantLock 可重入锁
    //ReentrantLock 和 synchronized 区别，synchronized 是一个关键字，
    // 背后逻辑是 JVM 实现的，ReentrantLock 是一个标准库中的类，要谨防忘记释放。
    // synchronized 如果竞争锁失败，就会阻塞等待 但是 ReentrantLock 除了阻塞这一个手段之外，还有 t

    //ReentrantLock 基础用法：lock()   unlock() 把加锁和解锁操作分开了，分开的做法不太好，
    // 容易遗忘 unlock ，就会导致死锁。一般就是通过 try finally ，把 unlock 放到 unlock里面
    // 多个线程竞争同一个锁的时候，也会阻塞
    //synchronized：是一个关键字（背后的逻辑史 JVM 内部实现的，C++），
    // ReentrantLock 是一个标准库当中的类（背后逻辑是 Java 写的）
    //synchronized 不需要收到释放锁，除了代码块，锁自然释放。ReentrantLock 必须手动释放锁，要谨记忘记释放
    //synchronized 如果竞争锁失败的时候，就会阻塞等待，但是 ReentrantLock 除了阻塞等待这一手之外，
    // 还有一手：trylock 失败了直接返回
    //synchronized 是一个非公平锁。 ReentrantLock 提供了非公平和公平锁两个版本，在构造方法中，
    // 通过参数来指定是公平锁还是非公平锁。
    //基于 synchronized 衍生出来的等待机制，是 wait 和 notify。相对功能有限。
    // 基于 ReentrantLock 衍生出来的等待机制，是 Condition 类（条件变量），功能要更丰富一些。

    //日常开发当中，绝大部分情况下，synchronized 就够用了。
    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();//括号里面加上 true 就是公平锁
        //加锁
        locker.lock();
        //解锁
        locker.unlock();
    }
}
