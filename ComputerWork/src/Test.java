public class Test {

    //CPU 当中的电磁继电器，通过通电和不通电来得到 0 和 1 这样的数据，这样就产生了二进制。
    // 基于 CPU 的电子开关，就构造出基本的门电路。
    // 最基本的门电路（针对二进制位）：非门，与门（只有两个都为 1 才为 1），或门

    //半加器，全加器：求和是异或。进位是与
    //全加器里面是半加器

    //减乘除 都是通过加法器来实现的
    //通过补码来完成减法，乘法就是连续相加，除法就是连续相减

    //CPU 里面出了运算器，还有控制单元和寄存器。寄存器是 cpu 内部用来存储数据的组件
    //  寄存器访问速度很快很快，但是空间很小
    //断电之后数据丢失
    //控制单元（CU）：协调 CPU 来工作

    //指令（和编程密切相关）：机器语言。不同的 CPU 支持不同的 机器语言（指令）
    //编程语言分为三类：机器语言（二进制的数字来表示不同的操作），汇编语言，高级语言
    //汇编语言和机器语言是一对一的关系（完全等价）。
    // 不同的 CPU 支持的机器指令不一样，不同 CPU 上面跑的汇编也不一样。
    //指令是如何执行的：有 n 位操作码和 n 位地址码来操作

    //我们写的程序，最终都会被编译器给翻译成 CPU 所能识别的机器语言指令。
    //在运行程序的时候，操作系统把这样的可执行程序加载到内存当中，cpu 就一条一条指令的去进行读取，解析和执行
    //如果再搭配上条件跳转，就能实现 条件语句 和 循环语句。


    //关于操作系统：是计算机上面最重要，最复杂的软件之一
    // 操作系统管理进程，进程：跑起来的应用程序。 线程：进程内部的一部分，进程包含线程。
    //写的代码，最终的目的都是跑起来，成为一些进程。Java代码都是通过 JVM 跑起来的

    //操作系统是如何管理进程的：
    // 1、先描述一个进程（明确进程上面的一些相关属性）
    // 2、在组织若干个进程（使用数据结构，把很多信息给放到一起，方便进行增删查改），典型的实现：双向链表
    //      通过双向链表，把每个进程的 PCB 连起来。操作系统种类很多，这里用 Linux 来讨论
    //操作系统龙猫主要的是通过 C/C++ 来实现的，所以这里的描述就是用：结构体 来描述的，就是 Java 的类
    // 不过结构体功能更简单。操作系统当中描述进程的结构体，叫做：PCB（进程控制块）

    //所谓的创建进程，就是创建出 PCB，然后加到双向链表当中。销毁线程，就是找到 PCB，然后从链表当中删除
    // 查看任务管理器，就是遍历链表。很多代码，还是落在数据结构上面

    //PCB 当中的一些属性：pid（进程id）进程的身份标识
    // 内存指针：指明了这个进程要执行的代码/指令在内存的哪里，以及这个进程当中依赖的数据都在哪里。
    // 运行 exe 文件的时候，操作系统会把 exe 加载到内存当中，变成进程。进程要执行的是二进制指令
    // 文件描述符表：程序运行过程当中，和文件处理有关。每次打开一个文件，就会在文件描述符表上多增加一项，
    //      文件描述符表可以看作是一个数组，里面的每个元素又是一个结构体，对应一个文件的相关信息。
    //      一个进程只要启动，就会默认打开三个文件：标准输入，标准输出，标准错误。

    //实现进程调度：状态，优先级，上下文，记账信息。 进程调度（多任务操作系统）：一个系统同一时间，执行了很多的任务。
    //并发和并行。 并行：微观上：两个 CPU 核心，同时执行两个任务的代码
    //      并发：微观上，一个 CPU 先执行一会儿任务1，再执行任务2，再执行任务3，然后再执行任务一，这样来循环
    //          宏观上来看，切换的快，就好像很多任务在同时执行
    //进程的状态：描述了当前的进程接下来应该怎么调度。：
    // 就绪：随时可以去 CPU 上去执行，就是随叫随到。
    // 阻塞/睡眠 状态：暂时不可以去 CPU 上执行
    //进程的优先级：先给谁分配时间，后给谁分配时间。以及给谁分配的多，给谁分配的少。
    //进程的记账信息：统计每个进程都分别被执行了多久，分别都执行来哪些指令，分别都排队等了多久，给进程调度提供指导依据。
    //进程的上下文：表示上次进程被调度出 CPU 的时候，当时程序的状态，下次进程上 CPU 的时候，
    // 就恢复到之前的状态，然后继续往下执行。 就类似于存档和读档

    //进程的调度，其实就是 操作系统在考虑 CPU 资源如何给各个进程分配
    //内存资源是通过：虚拟地址空间来分配的，就是进程的独立性。
    //由于操作系统上，同时运行很多进程，某个进程出现 bug 并不会影响其它进程，因为做到了进程独立，就是使用了：虚拟地址空间
    // 如果发现某个程序出现 bug 了，就可能一起其它进程的崩溃。解决方案，就是给出很多线程，这些道路之间彼此隔离开，
    //  彼此走各自的道路，就不用担心引起其它进程的崩溃。
    //进程间通信：有些时候，进程是需要交互的，所以就提供一个公共空间来进行交互。一个进程把数据放到公共空间，另外一个进程来取走，
    // 这样的话，隔离性就不会被打破。

    //在操作系统当中，提供的”公共空间“有很多种，而且各有特点，有的存储空间大，有的小，有的快，有的慢。
    // 操作系统当中提供了很多种这样的进程间的通信方式。现在最主要的就是：文件操作，网络操作








}
