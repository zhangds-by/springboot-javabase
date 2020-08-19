package com.zhangds.netty;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTests {
    public static void main(String[] args) {

//        String origin = "验血错误：,备血错误：(自开立输血医嘱至完成备血),传送错误：,其他因素,不知道";
//        Set<String> strings = testStrSplit(origin, ",", "：", false);
//        System.out.println(strings);
//
//        String substring = origin.substring(0, origin.indexOf("："));
//        System.out.println(substring);

        try {
            testTimeChange();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static Set<String> testStrSplit(String str, String start, String end, boolean isSpecial){
        Set<String> result = new HashSet<>();
        if(isSpecial){
            start = "/" + start;
            end = "/" + end;
        }
            String regex = start + "(.*?)" + end;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            while(matcher.find()){
            String key = matcher.group(1);
            if(!key.contains(start) && !key.contains(end)){
                result.add(key);
            }
        }
        return result;
    }

    /**
     * 获取字符串的数字部分
     * @author zhangds
     * @date 2020/7/3 10:25
     */
    public static void testOnlyMath(){
        String timeSection = "10月";
        String regEx="[0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(timeSection);

        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
//        System.out.println(Integer.parseInt(res.toString()));
        String join = StringUtils.join(list.toArray(), "");
        System.out.println(join);
        System.out.println(Integer.parseInt(join));
    }


    public static void testTimeChange() throws ParseException {
        String beginTime = "09:13";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("HH:mm").parse(beginTime));
        System.out.println(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.MINUTE, Integer.valueOf("00"));
        System.out.println(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
    }
}
