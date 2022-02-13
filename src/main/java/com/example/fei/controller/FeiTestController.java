package com.example.fei.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fei.common.core.AjaxResult;
import com.example.fei.domain.User;
import com.example.fei.service.TokenService;
import com.example.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FeiTestController {
    @Autowired
    private UserService userService;

    /**
     * 测试接口
     */
    @RequestMapping("/test/detail")
    public AjaxResult detail()
    {
        AjaxResult ajax = AjaxResult.success("success");

        User user = userService.selectUser(1);

        ajax.put(AjaxResult.DATA_TAG, user);
        ajax.put("page_count", 0);

        return ajax;
    }

}
