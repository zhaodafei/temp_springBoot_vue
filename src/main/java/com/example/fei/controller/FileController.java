package com.example.fei.controller;

import com.example.fei.common.utils.FileUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;


@RestController
@RequestMapping("/file")
public class FileController {

    // 上传文件2
    /*@PostMapping("upload2")
    public HashMap<String, String> upload2(@RequestParam("file") MultipartFile file) {

        // String contentType = file.getContentType(); // 获取文件类型
        String fileName = file.getOriginalFilename(); // 图片名字
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile2(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }

        return new HashMap<String, String>() {{
            put("code", "200");
            put("msg", "上传成功");
        }};
    }*/

    // 上传文件3
    /*@PostMapping("upload3")
    public  HashMap<String, String> upload3(@RequestParam("file") MultipartFile uploadFile) {

        String fileName = uploadFile.getOriginalFilename();
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile3(uploadFile, filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }

        return new HashMap<String, String>() {{
            put("code", "200");
            put("msg", "上传成功");
        }};
    }*/


    // 下载文件2
    /*@PostMapping("download2")
    public ResponseEntity<InputStreamResource> download2() throws IOException {
        String fullFilePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\02.jpg";
        return  FileUtil.downloadFile2(fullFilePath);
    }*/


    // 上传文件
    @PostMapping("upload")
    public HashMap<String, String> upload(@RequestParam("file") MultipartFile uploadFile) {

        String fileName = uploadFile.getOriginalFilename();
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile(uploadFile.getInputStream(), filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }

        return new HashMap<String, String>() {{
            put("code", "200");
            put("msg", "上传成功");
        }};
    }

    // 文件下载
    @PostMapping("download")
    public String download2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";
        // // String fileName = "001.png";
        String fileName = "02.jpg";

        FileUtil.downloadFile(response,filePath,fileName);
        return null;
    }

}
