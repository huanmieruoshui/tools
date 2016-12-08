package com.hbl.utils.email;

/**
* @className:SendMail.java
* @classDescription:
* @author:hbl
* @createTime:2016年12月2日
*/
public class SendMailTest {

	public static void main(String[] args) throws Exception {
		String content = "测试邮件";
		String email = "***@***.com";
		sendMail(content, email);
	}
	
	private static void sendMail(String content, String email) {
		MailHandler handler = new MailHandler();
		String subject = "测试标题";
		String body = content;
		SmtpClient.send_Mail(handler, email, subject, body, null);
	}
}
