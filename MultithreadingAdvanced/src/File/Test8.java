package File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test8 {

    //写文件，使用 字节流
    public static void main(String[] args) {
        //每次按照写方式打开文件，都会清空原有文件的所有内容，然后再从起始位置往后写。
        // 于是就有了一种追加写的流对象，打开之后不清空，从文件末尾继续写。
        try (OutputStream outputStream = new FileOutputStream("d:/test.txt")) {
            byte[] buffer = new byte[]{97,98,99};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main1(String[] args) {
        try (OutputStream outputStream = new FileOutputStream("d:/test.txt")) {
            //一次写入一个字节
            outputStream.write(97);
            outputStream.write(98);
            outputStream.write(99);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
