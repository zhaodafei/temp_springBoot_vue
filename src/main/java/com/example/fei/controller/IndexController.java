package com.example.fei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    /**
     * 访问首页
     */
    @RequestMapping("/")
    public String index()
    {
        return "欢迎访问首页,请访问正确接口";
    }
}
