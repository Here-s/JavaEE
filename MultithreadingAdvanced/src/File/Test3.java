package File;

import java.io.File;
import java.io.IOException;

public class Test3 {

    public static void main(String[] args) {
        File f = new File("./Test1.txt");
        //删除文件，直接删除
        f.delete();
    }

    public static void main1(String[] args) throws IOException {
        //文件的创建和删除
        File f = new File("./Test1.txt");
        System.out.println(f.exists());
        System.out.println("创建文件");
        f.createNewFile();
        System.out.println("创建文件结束");
        System.out.println(f.exists());
    }
}
