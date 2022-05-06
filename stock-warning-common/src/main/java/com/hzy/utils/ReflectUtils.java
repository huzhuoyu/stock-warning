package com.hzy.utils;

import com.hzy.entity.StockDetailsInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectUtils
 * @Description 反射工具类
 * @Author huzhuoyu
 * @Date 2022/5/6 7:21 下午
 */
public class ReflectUtils {
    /**
     * 思路
     * 1. 传参包括2个对象，分别为源对象和目标对象，可以属于不同的类
     * 2. 获取两对象的Class
     * 3. 获取两对象的属性
     * 4. 遍历目标对象的属性，拼接set方法
     * 5. 遍历源对象的属性，拼接get方法
     * 6. 判断属于相同方法执行 target.setXxx(souce.getXxx)
     *  set的时候判断参数的类型
     * @Param source 源对象
     * @Param target 目标对象
     */
    //todo 需要修改此方法
    public static void copyValueToEntity(Object source, Object target) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        String s = "v_sh601658=\"1~邮储银行~601658~5.47~5.60~5.55~1127174~500115~627060~5.47~12244~5.46~10784~5.45~10739~5.44~8939~5.43~4726~5.48~6537~5.49~3231~5.50~1450~5.51~5127~5.52~5090~~20220506160001~-0.13~-2.32~5.59~5.46~5.47/1127174/621528316~1127174~62153~1.00~6.32~~5.59~5.46~2.32~616.71~5053.40~0.77~6.16~5.04~0.59~25997~5.51~5.06~6.63~~~0.62~62152.8316~0.0000~0~ ~GP-A~7.25~2.43~3.81~9.49~0.60~6.09~4.49~-2.32~-3.01~-2.67~11274461250~92383970000~37.75~13.49~11274461250~\";";
        int start = s.indexOf("\"");
        int end = s.lastIndexOf("\"");
        String sub = s.substring(start + 1, end);
        sub = sub.replaceAll("~~","~null~");
        String[] split = sub.split("~");
        System.out.println(sub);
        StockDetailsInfo stockDetailsInfo = new StockDetailsInfo();
        Class<?> clazz = stockDetailsInfo.getClass();
        Field field[] = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            String targetFirstUpper = name.substring(0, 1).toUpperCase();
            String setName = "set" + targetFirstUpper + name.substring(1);
            Method setMethod = clazz.getMethod(setName, String.class);
            setMethod.invoke(stockDetailsInfo, split[i]);
        }
        System.out.println(stockDetailsInfo);
    }

}
