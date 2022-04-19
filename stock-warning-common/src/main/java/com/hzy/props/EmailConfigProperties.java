package com.hzy.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmailConfigProperties
 * @Description 发送短信配置
 * @Author huzhuoyu
 * @Date 2022/4/1 8:11 下午
 */
@ConfigurationProperties(prefix = EmailConfigProperties.PREFIX_EMAIL)
@Component
@Data
public class EmailConfigProperties {
    /**
     * 配置前缀
     */
    public static final String PREFIX_EMAIL = "stock.warning";
    /**
     * 发送邮箱对象
     */
    private Email email = new Email();

    @Data
    public class Email {
        //认证
        private String auth = "true";
        //邮件服务器
        private String host = "smtp.qq.com";
        //用户
        private String user = "huzhuoyujob";
        //后缀
        private String suffix = "@qq.com";
        //授权码
        private String password = "yfsxrizsfotnbbde";
    }
}
