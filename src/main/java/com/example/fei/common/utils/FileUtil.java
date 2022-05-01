package com.example.fei.common.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//文件上传工具类
public class FileUtil {

    // 文件上传2
    /*public static void uploadFile2(byte[] file,String filePath,String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) { // 创建目录
            targetFile.mkdirs();
        }

        FileOutputStream  out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }*/

    // 采用MultipartFile的transfer() 实现文件上传3
    /*public static void uploadFile3(MultipartFile uploadFile, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath + fileName);
        uploadFile.transferTo(targetFile);
    }*/

    // 二进制流文件下载2
    /*public static ResponseEntity<InputStreamResource> downloadFile2(String fullFilePath) throws IOException {
        // String fullFilePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\02.jpg";
        FileSystemResource file = new FileSystemResource(fullFilePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment;fileName=%s", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("web-file-type", "image/jpeg"); // 自定义文件类型

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }*/


    public static void uploadFile(InputStream fileStream, String filePath, String fileName) throws IOException {
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

    public static void downloadFile(HttpServletResponse response,String filePath,String fileName) {
        // String filePath = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";
        // String fileName = "001.png";
        // String fileName = "02.jpg";
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名  URLEncoder.encode(fileName, "utf-8")
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("web-file-type", "image/jpeg"); // 自定义文件类型

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            File file = new File(filePath, fileName);
            byte[] buffer = new byte[1024];
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ServletOutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
