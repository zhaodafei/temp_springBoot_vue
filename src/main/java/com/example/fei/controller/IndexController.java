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
    {   // ctrl + 回车,自动添加连接符换行
        return "欢迎访问首页,请访问正确接口;<br/>" +
                "美好的一天开始了,<br/>" +
                "请大家努力工作,<br/>" +
                "迎娶白富美,<br/>" +
                "嫁个高富帅,<br/>" +
                "踏上人生巅峰";
    }
}
