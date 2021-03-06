package com.hzy.autoconfig;

import com.hzy.props.EmailConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

/**
 * @ClassName StockWarningConfiguration
 * @Description 自动装载类
 * @Author huzhuoyu
 * @Date 2022/4/20 12:11 上午
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
