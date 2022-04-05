package File;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test11 {

    //案例一：查找文件并删除
    //文件操作的案例：用户输入一个目录，再输入一个要删除的文件名。
    // 找到名称中包含指定字符的所有普通文件（不包含目录），并且询问用户是否要删除该文件
    public static void main(String[] args) {
        //先输入要扫描的目录，以及要删除的文件名。
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的路径");
        String rootDirPath = scanner.next();
        System.out.println("请输入要删除的文件名");
        String teDeleteName = scanner.next();
        File rootDir = new File(rootDirPath);
        if (!rootDir.isDirectory()) {
            System.out.println("输入的扫描路径有误");
            return;
        }
        //输出所有的目录。输出的时候，遍历方式也是递归
        scanDir(rootDir,teDeleteName);
    }
    private static void scanDir(File rootDir, String teDeleteName) {
        //1、先列出 rootDir 中有哪些内容。
        File[] files = rootDir.listFiles();
        if (files == null) {
            //rootDir 是一个空目录
            return;
        }
        //遍历当前列出的这些内容，如果是普通文件，就检测文件名是否是要删除的文件。
        // 如果是目录，就递归进行遍历
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().contains(teDeleteName)) {
                    deleteFile(f);
                }
            } else if (f.isDirectory()) {
                scanDir(f,teDeleteName);
            }
        }
    }
    private static void deleteFile(File f) {
        try {
            System.out.println(f.getCanonicalPath() + "确定要删除吗(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equals("Y") || choice.equals("y")) {
                f.delete();
                System.out.println("文件删除成功");
            } else {
                System.out.println("文件取消删除");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
