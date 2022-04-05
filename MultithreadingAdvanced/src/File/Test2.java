package File;

import java.io.File;

public class Test2 {

    public static void main(String[] args) {
        //换成相对路径就全是 false 了
        File f = new File("./Test1.txt");
        //判断文件是否存在
        System.out.println(f.exists());
        //判断文件是否是一个目录
        System.out.println(f.isDirectory());
        //判断文件是否是一个普通文件
        System.out.println(f.isFile());
    }

    public static void main1(String[] args) {
        File f = new File("d/Test1.txt");
        //判断文件是否存在
        System.out.println(f.exists());
        //判断文件是否是一个目录
        System.out.println(f.isDirectory());
        //判断文件是否是一个普通文件
        System.out.println(f.isFile());
    }
}
