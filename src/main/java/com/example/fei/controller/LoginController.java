package com.example.fei.controller;

import com.example.fei.common.constant.CacheConstants;
import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.core.redis.RedisCache;
import com.example.fei.domain.User;
import com.example.fei.service.TokenService;
import com.example.fei.service.UserService;
import com.example.fei.service.SwitchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SwitchConfig switchConfig;

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

            // 验证码
            if (switchConfig.isLoginCaptcha) {
                int errorCode = validateCaptcha(user.getCode(), user.getUuid());
                if (errorCode != 0) {
                    return AjaxResult.error("验证码错误");
                }
            }

            AjaxResult ajax = AjaxResult.success("success");
            ajax.put(AjaxResult.DATA_TAG, loginInfo);
            return ajax;
        } else {
            return AjaxResult.error("用户不存在");
        }
    }

    public int validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;;
        String captcha = redisCache.getCacheObject(verifyKey);
        System.out.println("2从redis获取到验证码" + captcha);
        if (captcha == null) {
            System.out.println("空值,抛异常");
            return 1;
        }
        if (!code.equalsIgnoreCase(captcha)) {
            System.out.println("校验失败,抛异常");
            return 2;
        }

        return 0;
    }
}
