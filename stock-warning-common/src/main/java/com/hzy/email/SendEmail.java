package com.hzy.email;

import com.hzy.contants.StockContants;
import com.hzy.exception.StockException;
import com.hzy.props.EmailConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName SendEmail
 * @Description 发送邮件
 * @Author huzhuoyu
 * @Date 2022/4/1 5:15 下午
 */
@Component
@Slf4j
public class SendEmail {
    @Autowired
    EmailConfigProperties emailConfigProperties;

    @Autowired
    Properties emailConfiguration;

    //是否发送成功标识
    AtomicBoolean isSendSuccess = new AtomicBoolean(false);

    public boolean sendMail(MailSenderInfo mailInfo) {
        try {
            //发件人邮箱地址
            String user = emailConfigProperties.getEmail().getUser();

            //发件人邮箱密码
            String password = emailConfigProperties.getEmail().getPassword();

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new EmailAuthenticator(user, password);

            // 使用环境属性和授权信息，创建邮件会话
            Session session = Session.getInstance(emailConfiguration, authenticator);

            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);

            // 设置发件人
            InternetAddress from = new InternetAddress(user + emailConfigProperties.getEmail().getSuffix());
            message.setFrom(from);

            // 发送给收件人/多人
            String toList = getMailList(mailInfo.getToAddress().split(","));
            InternetAddress[] iaToList = new InternetAddress().parse(toList);
            message.setRecipients(Message.RecipientType.TO, iaToList);

            // 设置邮件标题
            message.setSubject(mailInfo.getTitle());

            // 设置邮件消息发送的时间
            message.setSentDate(new Date());

            // 设置邮件的内容体
            message.setContent(mailInfo.getTest(), "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
            isSendSuccess.set(true);
            return isSendSuccess.get();
        } catch (Exception e) {
            isSendSuccess.set(false);
            if (log.isErrorEnabled()) {
                log.error("发送邮件失败……");
            }
            throw new StockException(StockContants.FAILED, "发送邮件失败……", e);
        }
    }

    private String getMailList(String[] mailArray) {
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }
            }
        }
        return toList.toString();
    }
}