package com.hzy.email;

import com.hzy.props.EmailConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName SendEmail
 * @Description 发送邮件
 * @Author huzhuoyu
 * @Date 2022/4/1 5:15 下午
 */
@Component
public class SendEmail {
    @Autowired
    EmailConfigProperties emailConfigProperties;

    @Autowired
    Properties emailConfiguration;

    //是否发送成功标识
    AtomicBoolean isSendSuccess = new AtomicBoolean(false);

    public boolean sendMail(String to, String text, String title) {
        try {
            //发送邮箱用户名
            String user = emailConfigProperties.getEmail().getUser();
            //发送邮箱密码
            String password = emailConfigProperties.getEmail().getPassword();
            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session session = Session.getInstance(emailConfiguration, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            InternetAddress from = new InternetAddress(user + emailConfigProperties.getEmail().getSuffix());
            message.setFrom(from);
            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            isSendSuccess.set(true);
            return isSendSuccess.get();
        } catch (Exception e) {
            isSendSuccess.set(false);
            e.printStackTrace();
        }
        return isSendSuccess.get();
    }
}