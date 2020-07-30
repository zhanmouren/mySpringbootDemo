package com.koron.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTest {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
		String currentMonth = "2019-09";
		Date currentDate = sdf.parse(currentMonth);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		//java 根据当前月的"yyyy-MM"获得上一个月“yyyy-MM”
		//calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		//java 根据当前月的"yyyy-MM"获得下一个月“yyyy-MM”
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		System.out.println(sdf.format(calendar.getTime()));
		} catch (Exception e) {
		e.printStackTrace();
		}
		getNextDate();
		getNextDate1();
	}
	//获取下个月第一天
	public static void getNextDate() {
		  try
         {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			  Date date=sdf.parse("2015-12");
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(date);
			  calendar.add(Calendar.MONTH,1);
			  System.out.println(sdf2.format(calendar.getTime()));
			  
         }
		  catch (Exception e) {
				e.printStackTrace();
	     }
	}
	
	//获取下个月同一天
		public static void getNextDate1() {
			  try
	         {
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				  Date date=sdf.parse("2015-09-05");
				  Calendar calendar = Calendar.getInstance();
				  calendar.setTime(date);
				  calendar.add(Calendar.MONTH,1);
				  System.out.println(sdf2.format(calendar.getTime()));
				  
	         }
			  catch (Exception e) {
					e.printStackTrace();
		     }
		}
}
