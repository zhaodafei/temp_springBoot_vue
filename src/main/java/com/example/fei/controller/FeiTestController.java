package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class FeiTestController {

    /**
     * 测试接口
     */
    // @RequestMapping("/test/detail")
    // public AjaxResult detail()
    // {
    //     AjaxResult ajax = AjaxResult.success("success");
    //
    //     HashMap<String, String> hashMap = new HashMap<String, String>() {{
    //         put("id", "111111");
    //         put("username", "daFei__daFei__daFei");
    //     }};
    //     ajax.put(AjaxResult.DATA_TAG, hashMap);
    //     ajax.put("page_count", 0);
    //
    //     return ajax;
    // }

    @RequestMapping("/test/detail")
    public HashMap<String, String> detail()
    {
        return new HashMap<String, String>() {{
            put("error", "200");
            put("msg", "success");
            put("foo_1", "测试接口访问成功");
        }};
    }

}
