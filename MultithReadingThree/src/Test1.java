
//通过 Singleton 这个类来实现单例模式，保证 Singleton 这个类只有一个实例
//饿汉模式
class Singleton {
    //static 修饰的成员更准确的说，应该叫作“类成员” => “类属性/类方法”
    //不加 static 的成员，就是“实例成员” => “实例属性/实例方法”
    //在 Java 程序中，一个类对象只存在一份（JVM 来保证），进一步也就保证了类的 static 成员只有一份
    //static 表示的意思和字面这个单词，没有任何联系。
    //类对象和对象不是一个东西。类：相当于实例的模板，基于模板可以创建出很多对象
    // 类对象：类名字.class 文件，被 JVM 加载到内存之后，表现出的模样。
    // 类对象里面就有 .class 文件中的一切信息。包括：类名，属性

    //使用 static 来创建实例，并且进行实例化。这个 instance 对于的实例，就是该类唯一的实例
    // 针对这个唯一实例的初始化，比较着急，类加载阶段，就会直接创建实例。（程序中用到这个类，就会立即加载）
    private static Singleton instance = new Singleton();
    //为了放在 程序员 在其他地方不小心 new 这个 Singleton 就可以把构造方法设为 private
    private Singleton() {

    }
    //通过一个方法，让外面能够拿到唯一实例
    public static Singleton getInstance() {
        return instance;
    }
}


public class Test1 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();//这样拿到的就是唯一实例
    }

    //线程的概念：
    //Thread 类的用法
    //线程的状态
    //线程安全的问题
    //wait / notify 调整执行顺序

    //多线程案例：
    // 1、线程安全的单例模式（面试常考，开发者常用）
    //  单例模式的两种典型实现：1、饿汉模式 2、懒汉模式
    //      饿汉模式：就是着急的意思，有啥事就做。比较着急去创建实例
    //      懒汉模式：就是留着东西，用多少，做多少。计算机当中用这个，效率高。
    //       不着急去创建实例，用的时候才真正创建
    //  单例模式主要解决的问题，是一个线程安全的单例模式。线程安不安全，是指在多线程环境下，
    //      并发调用 getInstance 方法，是否可能存在 bug
    //  饿汉模式：仅仅是读取了变量的内容，所以是线程安全的
    //  懒汉模式：因为会修改，所以是线程不安全的。

    //经典面试题：单例模式

}
