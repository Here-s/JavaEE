package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test10 {

    //按照字符来写
    public static void main(String[] args) {
        try (Writer writer = new FileWriter("d:/test.txt")) {
            writer.write("syz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
