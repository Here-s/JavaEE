package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//服务器端代码
public class UdpEchoServer {

    //进行网络编程，第一步就是先准备好 socket 实例，是进行网络编程的大前提
    private DatagramSocket socket = null;

    //这里的端口号，在运行程序的时候来指定就好。端口号可以自己定，也可以系统分配
    // 自己定端口号的时候，要注意范围
    //多个进程不能绑定同一个端口，一个进程可以绑定多个端口，就是创建多个 socket 对象，然后绑定多个端口
    // 构造 socket 对象失败的原因：
    // 1、端口号已经被占用
    // 2、每个进程能够打开的文件数是有上限的，如果进程之前就已经打开了很多很多的文件，
    //    就可能导致此处的 socket 文件就不能顺利打开了
    public UdpEchoServer(int port) throws SocketException {
        //在构造服务器这边的 socket 对象的时候，就需要显式的绑定一个端口号
        // 端口号式用来区分一个应用程序的，主机收到网卡上的数据的时候，这个数据该给那个程序
        socket = new DatagramSocket(port);
    }
    //启动服务器
    public void start() throws IOException {
        System.out.println("启动服务器");
        //UDP 不需要建立连接，直接接收从客户端发来的数据即可。
        while (true) {
            //1、读取客户发来的请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(requestPacket);//为了接收数据，需要准备好一个空的 DatagramPacket 对象，
                        // 由 receive 来进行填充数据.receive 方法是可能会阻塞
            //把接收到的数据转化为 String
            String request = new String(requestPacket.getData(),0,requestPacket.getLength(),"UTF-8");
            //2、根据请求计算响应（由于这是一个回显服务，所以这一步就可以省略）
            String response = process(request);
            //3、把响应写回到客户端。
            // send 方法的参数，也是 DatagramPacket 需要把响应数据先构造成一个 DatagramPacket 再进行发送
            // 这里就不是构造一个空的数据发送报
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());//这样就获取到了 IP 地址和 端口。
            //在发生数据的时候，必须指定这个数据要发给谁，就是指定 IP 和 端口
            // 在当前的情景当中，就是谁发来的请求，就再返还给谁。
            socket.send(responsePacket);
            System.out.printf("[%s:%d] req: %s, resp: %s\n",
                    requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
        }
    }
    //由于是回显服务，响应就和请求一样了
    // 对于真实的服务器来说，这个过程是复杂的。为了实现这个过程，可能需要几万行，几十万行代码
    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9090);
        server.start();
    }
}
