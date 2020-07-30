package com.koron.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * 定时任务工具类
 * corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期中的日期 年份  基于注解
 * 年份可选  月份中的日期和星期中的日期 两个一个要有？
 */
//@Component
public class ScheduledTasks {
	@Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次 
    public static void testCron() {
       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       System.out.println("每5秒执行一次"+sdf.format(new Date()));
    }
	@Scheduled(cron="0/2 * * * * ? ")   //每2秒执行一次 
    public void testCron2() {
       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       System.out.println("每2秒执行一次"+sdf.format(new Date()));
    }
   
   @Scheduled(cron="0/3 * * * * ? ")   //每3秒执行一次 
    public void testCron3() {
       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       System.out.println("每3秒执行一次"+sdf.format(new Date()));
    }
}
