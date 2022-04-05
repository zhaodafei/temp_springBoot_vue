package com.example.fei.controller;

import com.example.fei.common.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/file")
public class FileController {

    // 上传文件
    @PostMapping("upload")
    public void upload(@RequestParam("file") MultipartFile file) {

        // String contentType = file.getContentType(); // 获取文件类型
        String fileName = file.getOriginalFilename(); // 图片名字
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }
    }
    // 上传文件2
    @PostMapping("upload2")
    public void upload2(@RequestParam("file") MultipartFile uploadFile) {

        String fileName = uploadFile.getOriginalFilename();
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile2(uploadFile.getInputStream(), filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }

    }

    // 上传文件3
    @PostMapping("upload3")
    public void upload3(@RequestParam("file") MultipartFile uploadFile) {

        String fileName = uploadFile.getOriginalFilename();
        String filePath  = "E:\\self_web\\git_dev\\vue\\zFei_springBoot\\target\\";

        try {
            FileUtil.uploadFile3(uploadFile, filePath, fileName);
        } catch (Exception e) {
            // TODO: 处理异常信息
        }

    }

    // 下载文件
    @GetMapping("download")
    public void download() {
    }
}
