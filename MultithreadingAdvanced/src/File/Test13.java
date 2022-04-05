package File;

import java.io.*;
import java.util.Scanner;

public class Test13 {
    //.flash 就是刷新缓冲区
    //synchronized：能够满足原子性。并不能保证内存可见性。
    //volatile：最大的用处就是禁止指令重排序

    //文件内容的查找
    //先输入一个路径
    //在输入一个要查找的文件内容的关键词
    //递归的遍历文件，找到看哪个文件里的内容包含了关键词，就把对应的文件路径打印出来
    //先遍历递归文件，然后每个文件都打开，然后看看有没有一样的关键字，用字符串查找即可。

    public static void main(String[] args) {
        //输入要扫描的文件路径
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的路径");
        String rootDirPath = scanner.next();
        System.out.println("请输入要查询的关键词");
        String word = scanner.next();
        File rootDir = new File(rootDirPath);
        if (!rootDir.isDirectory()) {
            System.out.println("输入的路径违法");
            return;
        }
        //递归并遍历，针对普通文件和目录分别处理
        scanDir(rootDir, word);
    }
    private static void scanDir(File rootDir, String word) {
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
                //针对文件内容进行查找
                if (containsWord(f, word)) {
                    try {
                        System.out.println(f.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (f.isDirectory()) {
                scanDir(f,word);
            }
        }
    }
    private static boolean containsWord(File f, String word) {
        //写代码，慎重使用缩写，可读性会降低。
        StringBuilder stringBuilder = new StringBuilder();
        //把 f 中的内容都读出来，放到 StringBuilder 中
        try (Reader reader = new FileReader(f)) {
            char[] buffer = new char[1024];
            while (true) {
                int len = reader.read(buffer);
                if (len == -1) {
                    break;
                }
                stringBuilder.append(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //indexOf 返回的是子串的下标，如果 word 在 StringBuilder 中不存在，就返回 -1
        return stringBuilder.indexOf(word) != -1;
    }
}
