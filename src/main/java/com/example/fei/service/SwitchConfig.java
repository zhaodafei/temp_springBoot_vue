package com.example.fei.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件中的开关
 */
@Component
public class SwitchConfig {
    // 令牌自定义标识 ---------- 后期定义到配置文件中
    @Value("${switchFei.isLoginCaptcha}")
    public Boolean isLoginCaptcha;
}
