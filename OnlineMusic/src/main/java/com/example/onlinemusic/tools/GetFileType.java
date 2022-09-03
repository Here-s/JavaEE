package com.example.onlinemusic.tools;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Data
public class GetFileType {
    private static HashMap<String,String> fileHeadMap = new HashMap<>();

    static {
        //检测音频
        fileHeadMap.put("494433030000","mp3");
        fileHeadMap.put("494433040000","MP3");
        fileHeadMap.put("664c61430000","flac");//网易云无损音频文件
    }

    /**
     * 获取文件类型
     * @param filePath 文件路径
     * @return  文件类型
     * @throws IOException
     */
    public static String getFileHead(String filePath)throws IOException {
        //获取文件头
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer fileHead = new StringBuffer();
        for(int i = 0 ; i<=5;i++ ){
            int flag = br.read();
            if(Integer.toHexString(flag).length()==1){
                fileHead = fileHead.append(0);
            }
            fileHead = fileHead.append(Integer.toHexString(flag));
        }
        br.close();
        //查询文件类型
        return fileHeadMap.get(fileHead.toString());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileHead("D:/下载/Test1.mp3"));
    }
}
