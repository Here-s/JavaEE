import java.io.*;

public class Test9 {

    //按照字符来读写
    public static void main(String[] args) {
        try (Reader reader = new FileReader("d:/test.txt")) {
            //按照字符来读
            while (true) {
                char[] buffer = new char[1024];
                int len = reader.read(buffer);
                if (len == -1) {
                    break;
                }
//                for (int i = 0; i < len; i++) {
//                    System.out.println(buffer[i]);
//                }
                String s = new String(buffer, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
