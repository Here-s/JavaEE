package network;

import java.io.IOException;
import java.util.HashMap;

//根据请求计算响应，是服务器最难的部分

//一个 TCP 服务器，不能让 UDP 客户端连上
public class TcpDictServer extends TcpThreadPoolEchoServer {
    private HashMap<String, String> dict = new HashMap<>();
    public TcpDictServer(int port) throws IOException {
        super(port);

        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("pig", "小猪");
    }

    @Override
    public String process(String request) {
        return  dict.getOrDefault(request,"没有此翻译");
    }

    public static void main(String[] args) throws IOException {
        TcpDictServer server = new TcpDictServer(9090);
        server.start();
    }
}
