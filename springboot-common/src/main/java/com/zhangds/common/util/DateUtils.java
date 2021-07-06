package com.zhangds.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        now.getYear(); // 年
        now.getMonthValue(); // 月
        now.getDayOfMonth(); // 日

        // 时间
        LocalTime time = LocalTime.now();
        time.plusHours(3); // 3个小时后

        now.plus(1, ChronoUnit.WEEKS); // 一周后日期
        now.minus(1, ChronoUnit.YEARS); // 一年前日期
        now.plus(1, ChronoUnit.YEARS); // 一年后日期

        Clock.systemUTC().millis(); // 获取当时时间戳
        Clock.systemDefaultZone().millis();

        // 转换格式
        LocalDate ofDate = LocalDate.of(2020, 01, 12);
        System.out.println("自定义日期" + ofDate);

        // 时间相等
        System.out.println(now.equals(ofDate));

        // 日期大小
        now.isAfter(ofDate);
        now.isBefore(ofDate);

        // 处理时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );

        // 日期格式化
        String dayAfterTommorrow = "20180205";
        LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);

        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = now.format(format1);
        LocalDate date2 = LocalDate.parse(str,format1);
    }

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
