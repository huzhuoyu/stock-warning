package com.hzy;

import org.springframework.boot.Banner;
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
        SpringApplication application = new SpringApplication(StockWarningApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
