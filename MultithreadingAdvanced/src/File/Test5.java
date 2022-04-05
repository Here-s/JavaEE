package File;

import java.io.File;
import java.util.Arrays;

public class Test5 {

    public static void main(String[] args) {
        File f = new File("./");
        //通过 File 对象来输出。
        System.out.println(Arrays.toString(f.listFiles()));
    }

    public static void main1(String[] args) {
        File f = new File("./");
        //把 ./ 目录下所有的目录全部列出来
        System.out.println(Arrays.toString(f.list()));
    }
}
