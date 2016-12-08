package com.hbl.utils.email;

import com.hbl.utils.properties.ConfigUtils;

/**
* @className:MailHandler.java
* @classDescription:邮件发送初始化类
* @author:hbl
* @createTime:2016年8月15日
*/
public class MailHandler {

	private final String host;
	private final String username;
	private final String passwd;
	private final String from;
	private final String fromName;
	private final int port;
	
	private final static String MAIL_HOST = "mail.host";
	private final static String MAIL_FROM = "mail.fromUser";
	private final static String MAIL_FROMNAME = "mail.fromUserName";
	
	public MailHandler() {
		this(ConfigUtils.getString(MAIL_HOST, "114.215.107.214"), ConfigUtils.getString(MAIL_FROM, "hbl@wukuisite.com"));
		//this(Config.getString(MAIL_HOST), Config.getString(MAIL_FROM));
	}
	
	public MailHandler(String host, String from) {
		this(host, from, ConfigUtils.getString(MAIL_FROMNAME));
	}

	public MailHandler(String host, String from, String fromName) {
		this(host, null, null, from, fromName);
	}
		
	public MailHandler(String host, String username, String passwd, String from, String fromName) {
		this(host, username, passwd, from, fromName, 25);
	}
	
	public MailHandler(String host, String username, String passwd, String from, String fromName, int port) {
		this.host = host;
		this.username = username;
		this.passwd = passwd;
		this.from = from;
		this.fromName = fromName;
		this.port = port;
	}

	public String getHost() {
		return host;
	}
	public String getUsername() {
		return username;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getFrom() {
		return from;
	}
	public String getFromName() {
		return fromName;
	}
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "[host=" + host + ", username=" + username + ", from=" + from + ", fromName=" + fromName
				+ ", port=" + port + "]";
	}
}