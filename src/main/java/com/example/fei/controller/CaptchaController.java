package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.utils.IdUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 验证码操作处理
 */
@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    /**
     * 生成登录验证码
     * 登录的时候,传参 uuid,到我redis中获取唯一的值, 前端参数 uuid+code 等于我redis的键,获取到的值
     * redis: { uuid+code: code }
     * @return {}
     */
    @GetMapping("/getCaptchaImg")
    public AjaxResult getCode() {
        AjaxResult ajax = AjaxResult.success("success");

        // if (true) { /* 是否启用验证码*/return ajax;}
        // 保存验证码信息, key_uuid
        String uuid = IdUtils.randomUUID();

        String code = captchaProducer.createText(); // 验证码 code, 等前端穿过来值和这个对比
        BufferedImage image = captchaProducer.createImage(code);
        // 存入redis { uuid+code: code }

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img",os.toByteArray());

        return ajax;
    }
}
