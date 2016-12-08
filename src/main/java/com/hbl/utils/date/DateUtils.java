package com.hbl.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/**
	 * Date日期转成 format格式
	 * @param date
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String converDate(Date date, String format){
		SimpleDateFormat sdf2 = new SimpleDateFormat(format);
		String item= sdf2.format(date);
		System.out.println(item);
		return item;
	}
	
	/**
	 * 从一个时间格式转换成另一个时间格式
	 * @param date 与fromFormat格式相同的时间
	 * @param fromFormat
	 * @param toFormat
	 * @return
	 */
	public static String converDate(String date, String fromFormat, String toFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(toFormat);
		try {
			Date d = sdf.parse(date);
			return sdf2.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取一年前的时间
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String getLastYear(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR,-1);//把日期往后增加一天.整数往后推,负数往前移动
		calendar.add(Calendar.DATE,1);
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		return sdf.format(date);
	}
	
	/**
	 * 获取一个月前的时间
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String getLastMonth(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		return sdf.format(date);
	}
	
	/**
	 * 获取一周前的时间
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String getLastWeek(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-7);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		return sdf.format(date);
	}
	
	/** 
	 * 时间unix转换 
	 * @param timestampString 
	 * 		unix格式的时间，String类型
	 * @param format
	 * 		时间格式，例如yyyyMMdd
	 * @return 
	 */ 
	public static String TimeStampDate(String timestampString, String format) {  
		Long timestamp = Long.parseLong(timestampString) * 1000;  
	   	String date = new SimpleDateFormat(format).format(new Date(timestamp));  
	   	return date;  
	}  
	  
	/** 
	 * 将时间unix转换为int类型 
	 * @param timeString
	 * 		有格式的String类型的时间，例如2016-01-01，该格式与format定义的格式要相同
	 * @param format
	 * 		时间格式，例如yyyyMMdd
	 * @return 
	 */  
	public static int DateToInt(String timeString, String format) {  
		int time = 0;  
		try {  
			SimpleDateFormat sdf = new SimpleDateFormat(format);  
			Date date = sdf.parse(timeString);  
			String strTime = date.getTime() + "";  
			strTime = strTime.substring(0, 10);  
			time = Integer.parseInt(strTime);  
		}  
		catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return time;  
	}
	   
	/**
	 * long转int时间，即转成Unix时间
	 * @param time
	 * @return
	 */
	public static int LongDateToInt(long time) {
		String timeStr = time + "";
		//System.out.println(timeStr);
		timeStr = timeStr.substring(0, 10);  
		int timeInt = Integer.parseInt(timeStr); 
		//System.out.println(timeInt);
		return timeInt;
	}
	   
	/**
	 * 获取今天
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String getToday(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取昨天
	 * @param format 例如yyyyMMdd
	 * @return format格式
	 */
	public static String getYesterday(String format) {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE,-1); 
		String yesterday = new SimpleDateFormat(format).format(cal.getTime());
		return yesterday;
	}
}
