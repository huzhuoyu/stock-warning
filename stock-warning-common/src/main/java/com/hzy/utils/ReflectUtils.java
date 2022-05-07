package com.hzy.utils;

import com.hzy.contants.StockContants;
import com.hzy.exception.StockException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectUtils
 * @Description 反射工具类
 * @Author huzhuoyu
 * @Date 2022/5/6 7:21 下午
 */
@Slf4j
public class ReflectUtils {
    /**
     * 股票返回的String类型数据反射到StockDetailsInfo实体类
     *
     * @param dataString
     * @param target
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void stringCopyValueToEntity(String dataString, Object target) {
        //去除首尾引号
        int start = dataString.indexOf("\"");
        int end = dataString.lastIndexOf("\"");
        String substring = dataString.substring(start + 1, end);
        //根据"~"分割字符串
        substring = substring.replaceAll("~~", "~null~");
        String[] split = substring.split("~");
        log.info(substring);
        Class<?> clazz = target.getClass();
        Field field[] = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            //首字母大写
            String targetFirstUpper = name.substring(0, 1).toUpperCase();
            //构建set方法
            String setName = "set" + targetFirstUpper + name.substring(1);
            //执行set方法设值
            Method setMethod = null;
            try {
                setMethod = clazz.getMethod(setName, String.class);
                setMethod.invoke(target, split[i]);
            } catch (Exception e) {
                if (log.isErrorEnabled()){
                    log.error("反射取值失败");
                }
                throw new StockException(StockContants.FAILED, "反射取值失败", e);
            }
        }
        if (log.isDebugEnabled()){
            log.debug(target.toString());
        }
    }
}
