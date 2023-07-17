package com.example.fei.common.constant;

/**
 * 缓存的 key 常量,
 *
 */
public class CacheConstants {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "fei_captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "fei_login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "fei_repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "fei_rate_limit:";

}
