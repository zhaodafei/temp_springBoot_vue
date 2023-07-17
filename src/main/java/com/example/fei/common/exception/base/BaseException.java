package com.example.fei.common.exception.base;

/**
 * 基础异常类,其他异常继承这个类
 * 待完善
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String module; // 所属模块
    private final String code; // 错误码
    private final Object[] args; // 错误码对应的参数
    private final String defaultMessage; // 错误消息

    public BaseException(String module, String code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
