package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


//一个服务器 可以有很多个客户端。产生多少客户端，要通过性能测试来看。
//通常情况下，一个服务器能调试给多个客户端提供服务。
// 也有特殊情况：一个服务器只给一个客户端提供服务（典型就是在分布式系统当中，两个节点之间的交互）
//客户端的代码

//五元组：一次通信是由 五个 核心信息描述出来的。源IP 源端口 目的IP 目的端口 协议类型

//站在客户端的角度：
// 源IP：本机IP  源端口：系统分配的端口  目的IP：服务器的IP  目的端口：服务器的端口  协议类型：UDP
//站在服务器的角度：
// 源IP：服务器程序本机的IP  源端口：服务器绑定的端口（此处手动指定了9090）
// 目的IP：包含在收到的数据报中（客户端的IP） 目的端口：包含在收到的数据报中（客户端的端口） 协议类型：UDP
public class UdpEchoClient {

    private DatagramSocket socket = null;
    private String serverIP;
    private int serverPort;

    public UdpEchoClient(String serverIP, int serverPort) throws SocketException {
        //此处的 port 是服务器的端口
        //客户端启动的的时候，不需要给 socket 指定端口，客户端自己的端口是系统自己分配的
        socket = new DatagramSocket();
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    //在客户端构造 socket 对象的时候，就不再手动指定端口号了，而是让系统指定一个空闲的端口号。
    // 因为服务器端口是手动指定的，所以客户端分配端口之后可以找到服务器的端口，就可以访问
    public UdpEchoClient() throws SocketException {
        socket = new DatagramSocket();
    }
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //1、从控制台读取用户输入的字符串
            String request = scanner.next();
            //2、把用户输入的内容，构造成一个 UDP 请求，并发送
            //  构造的信息包含两部分信息：1、数据的内容 request 字符串    2、数据要发给谁 服务器的 IP + 端口
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);
            //3、从服务器响应数据，并解析
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength(),"UTF-8");
            //4、把响应结果显示到控制台上
            System.out.printf("req: %s, resp: %s\n", request,response);
        }
    }

    public static void main(String[] args) throws IOException {
        //因为客户端和服务器在同一个端口上，使用的 Ip 仍然是 127.0.0.1 如果在不同的机器上面，就要修改 IP 了
        UdpEchoClient client = new UdpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
