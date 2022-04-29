package com.hzy.utils;

/**
 * @ClassName StringUtils
 * @Description 字符串工具类
 * @Author huzhuoyu
 * @Date 2022/4/24 5:31 下午
 */
public class StringUtils {
    public static boolean strIsEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean strIsNotEmpty(String s) {
        return s != null && !"".equals(s);
    }
}
