public class Test6 {

    //关于文件：平时说的文件一般都是指存储在硬盘上面的普通文件（txt，mp4，rar）
    //在计算机当中，文件可能是一个广泛的概念，不只是普通文件，还可以包括目录（目录文件）
    //操作系统当中，还会使用文件来描述一些其他的硬件设备或软件资源。
    //网卡，操作系统中把这样的硬件设备也给抽象成一个文件，用来简化开发。显示器/键盘 都被操作系统视作文件

    //文件的分类：
    // 1、文本文件：里面存储的是字符。文本文件本质上也是存字节的。
    //  但是文本文件当中，相邻的字节在一起刚好可以构成一个个的字符
    // 2、二进制文件：存储的是字节。这种的话，字节和字节就没有关系了

    //判断文件是文本或二进制文件：
    // 打开是乱码，就是二进制文件，不是乱码就是文本文件。

    //关于目录结构：
    // 计算机里，保存管理文件，是通过 操作系统 中的“文件系统” 这样的模块来负责的。
    // 文件系统当中，一般是通过“树形”结构来组织磁盘上面的文件的

    //操作系统当中：通过“路径”这样的概念来描述一个具体文件/目录的位置
    // 绝对路径：以盘符开头的。
    // 相对路径：以 . 或 .. 开头的，其中 . 表示当前路径 .. 表示当前路径的父目录（上记路径）
    //  说到相对路径，必须要有一个基准目录，相对路径就hi是从基准路径出发，按照一个啥样的路径找到的对应文件
    //即使是定位到同一个文件，如果基准目录不同，此时相对路径也不同

    ///Java 中操作文件主要有两类
    // 1、文件系统相关的操作：指的是通过“文件资源管理器”能够完成一些功能。
    //  列出目录中有哪些文件，创建文件，创建目录，删除文件，重命名文件.....
    //  Java 当中提供了一个 File 类，通过这个类来完成上述操作，File 类就描述了文件/目录。
    // 2、文件内容相关的操作
    public static void main(String[] args) {

    }
}
