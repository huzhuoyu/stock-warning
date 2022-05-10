package com.hzy.datasource;

import com.hzy.entity.StockDetailsInfo;
import com.hzy.exception.StockException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName
 * @Description
 * @Author huzhuoyu
 * @Date 2022/5/5 10:42 上午
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class StockDataSourceImplTest {

    @InjectMocks
    StockDataSourceImpl stockDataSource;

    @Test
    public void getAllStock() {
        stockDataSource.getAllStock();
    }

    @Test
    public void getStockDetails() {
        System.out.println(stockDataSource.getStockDetails("600397"));
        stockDataSource.getStockDetails("601658");
        stockDataSource.getStockDetails("002387");
        stockDataSource.getStockDetails("002384");
    }

    @Test
    public void Test() {
        String s = "v_sh601658=\"1~邮储银行~601658~5.47~5.60~5.55~1127174~500115~627060~5.47~12244~5.46~10784~5" +
                ".45~10739~5.44~8939~5.43~4726~5.48~6537~5.49~3231~5.50~1450~5.51~5127~5.52~5090~~20220506160001~-0.13~-2.32~5.59~5.46~5.47/1127174/621528316~1127174~62153~1.00~6.32~~5.59~5.46~2.32~616.71~5053.40~0.77~6.16~5.04~0.59~25997~5.51~5.06~6.63~~~~~0.0000~0~ ~GP-A~7.25~2.43~3.81~9.49~0.60~6.09~4.49~-2.32~-3.01~-2.67~11274461250~92383970000~37.75~13.49~11274461250~\";";
        int start = s.indexOf("\"");
        int end = s.lastIndexOf("\"");
        String sub = s.substring(start + 1, end);
        sub = sub.replaceAll("~~", "~null~");
        sub = sub.replaceAll("~~", "~null~");
        sub = sub.replaceAll(" +", "null");
        String[] split = sub.split("~");
        log.info(sub);
        StockDetailsInfo stockDetailsInfo = new StockDetailsInfo();
        Class<?> clazz = stockDetailsInfo.getClass();
        Field field[] = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            String targetFirstUpper = name.substring(0, 1).toUpperCase();
            String setName = "set" + targetFirstUpper + name.substring(1);
            Method setMethod = null;
            try {
                setMethod = clazz.getMethod(setName, String.class);
                setMethod.invoke(stockDetailsInfo, split[i]);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("反射失败");
                    throw new StockException("反射失败", e);
                }
            }

        }
        log.info(stockDetailsInfo.toString());
    }
}