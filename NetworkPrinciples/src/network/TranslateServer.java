package network;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

//客户端不变，把服务器代码进行调整
//主要调整 process 方法，读取请求并解析，把响应写回客户端。
// 服务器端的关键逻辑就是：根据请求处理响应。之后大部分只需要写 process 方法就好了
public class TranslateServer extends UdpEchoServer{
    private HashMap<String, String> translate = new HashMap<>();

    public TranslateServer(int port) throws SocketException {
        super(port);

        translate.put("cat","小猫");
        translate.put("dog","小狗");
        translate.put("pig","小猪");
    }

    @Override
    public String process(String request) {
        return translate.getOrDefault(request,"没有该词的翻译");
    }

    public static void main(String[] args) throws IOException {
        TranslateServer server = new TranslateServer(9090);
        server.start();
    }
}
