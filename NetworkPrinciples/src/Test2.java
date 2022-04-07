public class Test2 {

    //UDP socket 比 TCP 更简单，主要涉及到两个类：
    // DatagramSocket 数据报，这个对象对应到操作系统当中的一个 socket 文件
    //  平时说的文件，只是指普通文件（硬盘上的数据）。实际上，操作系统中的文件还可能表示了一些硬件设备/软件资源
    //  socket 文件，就对应着 ”网卡“ 这种硬件设备，从 socket 文件读数据，本质上就是读网卡，
    //  往 socket 文件写数据，本质上就是写网卡。可以认为 socket 文件就是一个遥控器，通过遥控器来操作网卡。
    // DatagramPacket 代表了一个 UDP 数据报，使用 UDP 传输数据的基本单位。
}
