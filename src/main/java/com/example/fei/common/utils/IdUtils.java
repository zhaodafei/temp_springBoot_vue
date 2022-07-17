package com.example.fei.common.utils;

import java.util.UUID;

public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取随机UUID,无连接线
     *
     * @return 随机UUID
     */
    public static String randomUUIDNoUnderline() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
