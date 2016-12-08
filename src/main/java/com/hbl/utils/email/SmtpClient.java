package com.hbl.utils.email;

import java.io.IOException;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbl.utils.properties.ConfigUtils;
import com.sun.net.ssl.internal.ssl.Provider;

/**
* @className:SmtpClient.java
* @classDescription:发送邮件
* @author:hbl
* @createTime:2016年8月15日
*/
@SuppressWarnings("restriction")
public class SmtpClient {
	
	private final static Logger logger = LoggerFactory.getLogger(SmtpClient.class);
	// 服务器验证
    private final static String MAIL_STMP_AUTH = "mail.smtp.auth";
	private MimeMessage mimeMsg;
	private String to;
	private String host;
	private Session session;
	private Properties props = new Properties();
	private String username;
	private String password;
	private Multipart mp;
	  
	public SmtpClient(String host, String name, String passwd, int port) {
		setNamePass(host, name, passwd);
	    this.props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    this.props.setProperty("mail.smtp.port", "" + port);
	    this.props.setProperty("mail.smtp.socketFactory.port", "25");
	    createMimeMessage();
	}
	
	/**
	 * 设置邮件发送host、用户名和密码
	 * @param host
	 * 		邮件发送服务器host
	 * @param name
	 * 		用户名
	 * @param passwd
	 * 		密码
	 */
	public void setNamePass(String host, String name, String passwd){
	    this.host = host;
	    this.username = name;
	    this.password = passwd;
	}
	
	private void createMimeMessage() {
	    this.session = Session.getDefaultInstance(this.props, null);
	    this.mimeMsg = new MimeMessage(this.session);
	    this.mp = new MimeMultipart();
	}
	
	/**
	 * 发送邮件
	 * @param mailHandler
	 * 		邮件发送初始化类
	 * @param to
	 * 		收件人邮箱
	 * @param subject
	 * 		邮件主题
	 * @param body
	 * 		邮件内容
	 * @return 邮件是否发送成功，true成功，false失败
	 */
	public static boolean send_Mail(MailHandler mailHandler, String to, String subject, String body, String[] imagePaths) {
		try {
			logger.info("Mail initialized: " + mailHandler);
			SmtpClient smtpClient = new SmtpClient(mailHandler.getHost(), mailHandler.getUsername(), mailHandler.getPasswd(), mailHandler.getPort());
			smtpClient.setNeedAuth(ConfigUtils.getBoolean(MAIL_STMP_AUTH, false));
			smtpClient.setTo(to);
			smtpClient.setSubject(subject);
			smtpClient.setBody(body);
			smtpClient.setFrom(mailHandler.getFrom(), mailHandler.getFromName());
			if(imagePaths!=null) {
				smtpClient.setImagePaths(imagePaths);
			}
			Security.addProvider(new Provider());
			return smtpClient.sendout();
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("when send to " + to + " , by " + mailHandler, e);
		}
		return false;
	}
	
	public boolean sendout() {
		try {
			this.mimeMsg.setContent(this.mp);
	    	this.mimeMsg.saveChanges();
	    	Session session = Session.getInstance(this.props, null);
	    	Transport transport = session.getTransport("smtp");
	    	transport.connect(this.host, this.username, this.password);
	    	transport.sendMessage(this.mimeMsg, this.mimeMsg.getRecipients(Message.RecipientType.TO));
	    	transport.close();
	    	logger.info("Send mail successful, to is: " + this.to);
	    	return true;
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	    	logger.error("Send mail failed, to is: " + this.to, e);
	    }
		return false;
	}
	
	public void setNeedAuth(boolean needAuth) {
	    if (needAuth)
	      this.props.put("mail.smtp.auth", "true");
	    else
	      this.props.put("mail.smtp.auth", "false");
	}
	
	public void setSubject(String subject) throws MessagingException {
		this.mimeMsg.setSubject(subject);
	}
	
	public void setBody(String body) throws MessagingException {
	    MimeBodyPart mimeBodyPart = new MimeBodyPart();
	    mimeBodyPart.setContent(body, "text/html;charset=GBK");
	    this.mp.addBodyPart(mimeBodyPart);
	}
	
	public void setTo(String to) throws AddressException, MessagingException {
		this.to = to;
		this.mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	}
	
	public void setFrom(String from, String fromName) throws MessagingException, IOException {
		this.mimeMsg.setFrom(new InternetAddress(from, fromName));
	}
	
	public void setImagePaths(String[] imagePaths) throws MessagingException {
		for(String path : imagePaths) {
			MimeBodyPart imagePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(path);
			imagePart.setDataHandler(new DataHandler(fds));
			imagePart.setHeader("Content-ID","<"+path.substring(path.lastIndexOf("/")+1)+">");
			this.mp.addBodyPart(imagePart);
		}
	}
}
