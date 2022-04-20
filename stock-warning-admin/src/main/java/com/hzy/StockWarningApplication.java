package com.hzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName StockWarningApplication
 * @Description StockWarningApplication启动类
 * @Author huzhuoyu
 * @Date 2022/4/2 9:29 上午
 */
@SpringBootApplication
public class StockWarningApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockWarningApplication.class, args);
        System.out.println("==========股票预警程序启动成功==========\n" +
                "███████╗████████╗ ██████╗  ██████╗██╗  ██╗     ██╗    ██╗ █████╗ ██████╗ ███╗   ██╗██╗███╗   ██╗ ██████╗ \n" +
                "██╔════╝╚══██╔══╝██╔═══██╗██╔════╝██║ ██╔╝     ██║    ██║██╔══██╗██╔══██╗████╗  ██║██║████╗  ██║██╔════╝ \n" +
                "███████╗   ██║   ██║   ██║██║     █████╔╝█████╗██║ █╗ ██║███████║██████╔╝██╔██╗ ██║██║██╔██╗ ██║██║  ███╗\n" +
                "╚════██║   ██║   ██║   ██║██║     ██╔═██╗╚════╝██║███╗██║██╔══██║██╔══██╗██║╚██╗██║██║██║╚██╗██║██║   ██║\n" +
                "███████║   ██║   ╚██████╔╝╚██████╗██║  ██╗     ╚███╔███╔╝██║  ██║██║  ██║██║ ╚████║██║██║ ╚████║╚██████╔╝\n" +
                "╚══════╝   ╚═╝    ╚═════╝  ╚═════╝╚═╝  ╚═╝      ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \n" +
                "                                                                                                         ");
    }
}
