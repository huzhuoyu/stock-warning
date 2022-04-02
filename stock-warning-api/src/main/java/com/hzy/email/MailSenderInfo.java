package com.hzy.email;

import lombok.*;

/**
 * @ClassName MailSenderInfo
 * @Description 短信对象
 * @Author huzhuoyu
 * @Date 2022/4/2 1:43 下午
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MailSenderInfo {
    // 收件人邮箱地址
    private String toAddress;
    // 邮件主题
    private String title;
    // 邮件的文本内容
    private String test;

    //创建对象的builder
    @Builder
    public MailSenderInfo(String toAddress, String title, String test) {
        this.toAddress = toAddress;
        this.title = title;
        this.test = test;
    }
}