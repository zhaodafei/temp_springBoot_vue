package com.example.fei.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类 todo:...
 */
public class DateUtils {
    public void fei() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdFormatter.format(nowTime));
    }
}
