package File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test7 {

    //文件内容相关的操作：打开，读，写，关闭。 int 可以让字节流和字符流统一起来。
    // 字节流对象，针对二进制文件，以字节为单位进行读写的：
    //  读：InputStream。   写：OutputStream   读：Reader   写：Writer 这些都是抽象类
    //   上面的这些抽象类，既可以针对普通对象进行读写，也可以针对特殊文件（网卡，socket 文件）进行读写
    //   在上面这些关键字前面加上 File 就是针对普通文件进行读写。上面类的使用方法都是一模一样的。
    //流：Stream 是一个形象的比喻。例如：想接 100 ml水，可以分为好几次来接。
    // 想通过流对象来读取 100 个字节，可以一次读十个字节，十次读完。也可以一次读 20 字节，五次读完。

    //FileInputStream
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream("d:/test.txt")){
            //一次读若干个字节
            while (true) {
                byte[] buffer = new byte[1024];
                //这个操作是把读出来的结果放到了 buffer 数组里了。相当于是使用 参数 来表示方法的返回值
                // 这种做法称为”输出型参数“，这种操作在 Java 中少见，C++ 当中常见。
                // 相当于把餐盘给阿姨，阿姨打好饭再给你
                int len = inputStream.read(buffer);
                if (len == -1) {
                    //读到了文件末尾，读取完毕
                    break;
                }
                for (int i = 0; i < len; i++) {
                    System.out.println(buffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main2(String[] args) {
        //这里没有显示的调用 close 但是 try 会帮我们调用，执行完 try 语句块之后，就自动调用 close
        // 不过需要实现 Closeable 接口，所有的流对象，都实现了 Closeable
        try (InputStream inputStream = new FileInputStream("d:/test.txt")){
            while (true) {
                int b = inputStream.read();
                if (b == -1) {
                    //读到了文件末尾
                    break;
                }
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //这种写法太啰嗦了，所有就可以使用 try with 来调整
    public static void main1(String[] args) {
        //方法中需要指定打开文件的路径。
        InputStream inputStream = null;
        try {
            //1、创建对象，同时也是在打开文件
            inputStream = new FileInputStream("d:/test.txt");
            //2、尝试一个一个字节的读，把文件都读完
            //读文件的时候，也可很容易读取失败。硬盘很容易出现坏道，
            while (true) {
                int b = inputStream.read();
                if (b == -1) {
                    //读到了文件末尾
                    break;
                }
                System.out.println(b);
            }
            //用完关闭文件，写在 finally 里面会更好，以为如果前面抛出异常的话，就跳过了这里的 close
            //inputStream.close();

            //read 有三个版本，
            // 版本一：无参数版本，一次读一个字节，返回值是读到的这个字节
            // 版本二：一个参数版本，一次读若干个字节，把读的结果放到参数中指定的数组当中，返回值就是读到的字节数
            // 版本三：三个参数版本：一次读若干个字节，把读的结果放到参数中指定的数组中，返回值就是读到的字节数，
            //      不是从数组的起始位置放置，而是从中间位置放置（off 这个下标的位置）len 表示最多能放多少个元素（字节）
            //
        } catch (FileNotFoundException e) {//这个异常，是 IOException 的子类。可以合并起来
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
