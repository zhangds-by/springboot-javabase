package com.zhangds.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static String format(Date date, String format) {
        if (date == null)
            return null;
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            log.debug(String.format("日期格式转化失败:%s,%s", date, format));
        }
        return null;
    }
}
