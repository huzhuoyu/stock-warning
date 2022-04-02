package com.hzy.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName StockWarningConfiguration
 * @Description StockWarningConfiguration-bean
 * @Author huzhuoyu
 * @Date 2022/4/2 9:03 上午
 */
@Configuration
public class StockWarningConfiguration {

    @Autowired
    EmailConfigProperties emailConfigProperties;

    //创建发送邮件配置bean,用于连接邮件服务器的参数配置
    @Bean
    public Properties emailConfiguration() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", emailConfigProperties.getEmail().getAuth());
        props.setProperty("mail.host", emailConfigProperties.getEmail().getHost());
        props.setProperty("mail.user", emailConfigProperties.getEmail().getUser());
        props.setProperty("mail.password", emailConfigProperties.getEmail().getPassword());
        return props;
    }
}
