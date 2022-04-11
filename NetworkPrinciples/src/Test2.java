public class Test2 {

    //UDP socket 比 TCP 更简单，主要涉及到两个类：
    //1、DatagramSocket 数据报，创建了一个 UDP 版本的 socket 对象，这个对象对应到操作系统当中的一个 socket 文件
    //  平时说的文件，只是指普通文件（硬盘上的数据）。实际上，操作系统中的文件还可能表示了一些硬件设备/软件资源
    //  socket 文件，就对应着 ”网卡“ 这种硬件设备，从 socket 文件读数据，本质上就是读网卡，
    //  往 socket 文件写数据，本质上就是写网卡。可以认为 socket 文件就是一个遥控器，通过遥控器来操作网卡。
    // 核心方法：receive 接收数据   send 发送数据    close 释放资源
    //2、DatagramPacket 代表了一个 UDP 数据报，使用 UDP 传输数据的基本单位。每次发送/接收数据，
    // 都是在传输一个 DatagramPacket 对象

    //最简单的客户端服务器程序：回显服务 EchoServer  这种是最简单的网络编程中的程序，不涉及到任何业务逻辑，
    // 就只是通过 socket api 单纯的转发


    //TCP 版本的客户端服务器的代码
    // TCP api中，也是涉及到两个核心的类：
    //  ServerSocket（专门给 TCP 服务器使用）
    //  Socket（既要给服务器用，又要给客户端用）
}
