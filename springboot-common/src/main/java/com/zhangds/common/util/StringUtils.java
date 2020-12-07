package com.zhangds.common.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstUpper(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }
}
