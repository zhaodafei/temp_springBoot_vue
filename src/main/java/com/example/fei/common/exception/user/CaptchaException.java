package com.example.fei.common.exception.user;

import com.example.fei.common.exception.base.BaseException;

// 待开发
public class CaptchaException extends BaseException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
