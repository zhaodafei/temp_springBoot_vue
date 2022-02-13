package com.example.fei.controller;

import com.example.fei.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private TokenService tokenService;

    /**
     * 测试 http://localhost:8080/login
     * @return {}
     */
    @RequestMapping("/login")
    public HashMap<String, String> login()
    {
        String token = tokenService.createToken();

        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", "1");
            put("token", token);
        }};
    }

    /**
     * 测试 http://localhost:8080/isLogin?foo=xxx
     * @param foo Sting 在login接口中获取的值
     * @return {}
     */
    @GetMapping("/isLogin")
    public HashMap<String, String> isLogin(String foo)
    {
        Boolean token = tokenService.parseJWT(foo);

        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", "1");
            put("token", String.valueOf(token));
        }};
    }
}
