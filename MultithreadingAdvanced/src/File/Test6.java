package File;

import java.io.File;

public class Test6 {

    public static void main(String[] args) {
        File f = new File("./aaa");
        File f2 = new File("./zzz");
        //把 aaa 的名字改成 zzz
        f.renameTo(f2);
    }
}
