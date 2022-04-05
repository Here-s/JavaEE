package File;

import java.io.File;

public class Test4 {


    public static void main(String[] args) {
        //创建多级目录
        File f = new File("./aaa/bbb/ccc/ddd");
        //这样就可以创建多级目录了
        f.mkdirs();
        System.out.println(f.isDirectory());
    }

    public static void main1(String[] args) {
        File f = new File("./aaa");
        //创建目录
        f.mkdir();
        //说明已经创建好目录了。
        System.out.println(f.isDirectory());
    }
}
