package com.hzy.email;

import lombok.NoArgsConstructor;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName EmailAuthenticator
 * @Description 邮件认证器
 * @Author huzhuoyu
 * @Date 2022/4/2 11:55 上午
 */
@NoArgsConstructor
public class EmailAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public EmailAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}