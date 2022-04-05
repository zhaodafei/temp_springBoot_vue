package com.example.fei.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

//文件上传工具类
public class FileUtil {
    public static void uploadFile(byte[] file,String filePath,String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) { // 创建目录
            targetFile.mkdirs();
        }

        FileOutputStream  out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static void uploadFile2(InputStream fileStream, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) { // 创建目录
            targetFile.mkdirs();
        }

        FileOutputStream  out = new FileOutputStream(filePath + fileName);
        //开始复制
        int i = 0;
        byte[] bytes = new byte[1024];
        while((i = fileStream.read(bytes))!=-1) {
            out.write(bytes, 0, i);
        }
        out.close();
        fileStream.close();
    }

    // 采用MultipartFile的transfer() 实现上传
    public static void uploadFile3(MultipartFile uploadFile, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath + fileName);
        uploadFile.transferTo(targetFile);
    }


}
