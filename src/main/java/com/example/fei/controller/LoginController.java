package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.domain.User;
import com.example.fei.service.TokenService;
import com.example.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * 测试 http://localhost:8080/login    @RequestMapping("/login")
     *
     * @return {}
     */
    @PostMapping("/loginXxx")
    public HashMap<String, String> loginXxx(@RequestBody User user)
    {
        String token = tokenService.createToken(user);

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

    // -------------------- 正式接口调用------------
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user)
    {
        // 判断用户是否存在
        User userObj = userService.selectByUsername(user);
        if (userObj != null) {
            String token = tokenService.createToken(user);

            HashMap<String, String> loginInfo = new HashMap<String, String>() {{
                put("access_token", token);
                put("username", user.getUsername());
                put("nickname", "请处理昵称_开发中");
                put("avatar", "头像地址");
            }};

            AjaxResult ajax = AjaxResult.success("success");
            ajax.put(AjaxResult.DATA_TAG, loginInfo);
            return ajax;
        }else{
            return AjaxResult.error("用户不存在");
        }
    }
}
