package com.example.fei.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fei.common.core.AjaxResult;
import com.example.fei.domain.User;
import com.example.fei.domain.User2;
import com.example.fei.service.TokenService;
import com.example.fei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 访问首页
     */
    @RequestMapping("/")
    public String index()
    {
        return "hello world index";
    }

    /**
     * 测试
     * @return {"msg":"自定义信息","code":"200","id":"1"}
     */
    @RequestMapping("/feiJson")
    public Map<String, String> index2()
    {
        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", "1");
            put("msg", "自定义信息");
        }};
    }

    /**
     * 访问首页
     * http://localhost:8080/select
     */
    @RequestMapping("/select")
    public AjaxResult selectUser() {
        AjaxResult ajax = AjaxResult.success();
        JSONObject object = new JSONObject();
        object.put("name", "fei");
        object.put("age", 18);

        User user = userService.selectUser(1);

        ajax.put(AjaxResult.DATA_TAG, user);
        ajax.put("foo", "自定义数据");
        ajax.put("bar", object);

        return ajax;
    }

    /**
     * 访问首页
     * http://localhost:8080/selectTwo
     */
    @RequestMapping("/select02")
    public AjaxResult selectUser2() {
        AjaxResult ajax = AjaxResult.success();
        JSONObject object = new JSONObject();
        object.put("name", "fei");
        object.put("age", 18);

        User user = userService.selectUser2(2);

        ajax.put(AjaxResult.DATA_TAG, user);
        ajax.put("foo", "自定义数据");
        ajax.put("bar", object);

        return ajax;
    }

    // @RequestMapping("/select03")
    // public AjaxResult selectUser3() {
    //     AjaxResult ajax = AjaxResult.success();
    //
    //     User user = userService.selectUser3(3);
    //
    //     ajax.put(AjaxResult.DATA_TAG, user);
    //
    //     return ajax;
    // }

    @RequestMapping("/select04")
    public AjaxResult selectUser4() {
        AjaxResult ajax = AjaxResult.success();

        // fei_tip: 模拟查询条件
        User userDomain = new User();
        userDomain.setId(4);
        userDomain.setUsername("dafei_04");

        User user = userService.selectUser4(userDomain);

        ajax.put(AjaxResult.DATA_TAG, user);

        return ajax;
    }

    @RequestMapping("/select05")
    public AjaxResult selectUser5() {
        AjaxResult ajax = AjaxResult.success();

        // fei_tip: 模拟查询条件
        User userDomain = new User();
        userDomain.setId(5);
        userDomain.setUsername("dafei_05");

        User user = userService.selectUser5(userDomain);

        ajax.put(AjaxResult.DATA_TAG, user);

        return ajax;
    }

    /**
     * 关联表查询
     * @return
     */
    @RequestMapping("/select06")
    public AjaxResult selectUser6() {
        AjaxResult ajax = AjaxResult.success();

        User2 user2 = userService.selectUser6(1);

        ajax.put(AjaxResult.DATA_TAG, user2);

        return ajax;
    }

    @RequestMapping("/select07")
    public AjaxResult selectUser7() {
        AjaxResult ajax = AjaxResult.success();

        User user = userService.selectUser7(1);

        ajax.put(AjaxResult.DATA_TAG, user);

        return ajax;
    }

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
        // System.out.println(foo);

        Boolean token = tokenService.parseJWT(foo);

        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", "1");
            put("token", String.valueOf(token));
        }};
    }

}
