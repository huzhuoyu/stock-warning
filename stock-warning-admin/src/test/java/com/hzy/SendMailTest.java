package com.hzy;

import com.hzy.props.EmailConfigProperties;
import com.hzy.email.MailSenderInfo;
import com.hzy.email.SendEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName com.hzy.SendMailTest
 * @Description com.hzy.SendMailTest
 * @Author huzhuoyu
 * @Date 2022/4/2 12:12 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockWarningApplication.class)
public class SendMailTest {
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    EmailConfigProperties emailConfigProperties;

    @Test
    public void sendMail() {
        boolean isSuccess;
        try {
            MailSenderInfo mailSenderInfo = new MailSenderInfo();
            mailSenderInfo.setToAddress("652355283@qq.com,masanke@163.com");
            mailSenderInfo.setTitle("这是一封测试邮件");
            mailSenderInfo.setTest("测试邮件11111");
            isSuccess = sendEmail.sendMail(mailSenderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        if (isSuccess) {
            System.out.println("===发送成功====");
        } else {
            System.out.println("===发送失败====");
        }
    }
}
