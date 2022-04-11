package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    //用普通的 socket 就好
    private Socket socket = null;

    public TcpEchoClient(String serverIP, int serverPort) throws IOException {
        //这里传入的 ip 和端口号 的含义表示的不是自己绑定，而是表示和这个 ip 端口建立连接
        // 调用这个构造方法，就可以和服务器建立连接
        //对于 UDP 的 socket 来说，构造方法指定的端口，表示自己绑定哪个端口
        //对于 TCP 的 ServerSocket 来说，构造方法指定的端口，也表示自己绑定哪个端口
        socket = new Socket(serverIP,serverPort);
    }

    public void start() {
        System.out.println("和服务器连接成功");
        Scanner scanner = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                while (true) {
                    //要做的事情，仍然是四个步骤
                    // 1、从控制台读取字符串
                    String request = scanner.next();
                    // 2、根据读取的字符串，构造请求，把请求发给服务器
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(request);
                    printWriter.flush();
                    // 3、从服务器读取响应，并解析
                    Scanner respScanner = new Scanner(inputStream);
                    String response = respScanner.next();
                    // 4、把结果显示到控制台上
                    System.out.printf("res: %s, resp: %s\n",request, response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
