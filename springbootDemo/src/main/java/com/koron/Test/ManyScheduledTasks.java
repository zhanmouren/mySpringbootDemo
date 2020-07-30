package com.koron.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 配置自定义任务，自定义时间,基于接口
 * @author cc
 *
 */
//@Configuration
public class ManyScheduledTasks implements SchedulingConfigurer {
	
	@Autowired
    private TaskScheduler myThreadPoolTaskScheduler;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		//也可以自定义的线程池，方便线程的使用与维护，这里不多说了
		scheduledTaskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler);
		 //简单粗暴的方式直接指定
		//scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
		//可以实现动态调整定时任务的执行频率
	     scheduledTaskRegistrar.addTriggerTask( ()->{
	       //任务逻辑
	    	  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  System.out.println("每3.0000秒执行一次"+sdf.format(new Date()));
	     }, triggerContext -> {
	    	 // 任务触发，在这里可修改任务的执行周期,因为每次调度都会执行这里
	         CronTrigger cronTrigger = new CronTrigger("0/3 * * * * ?");
	         //返回执行周期
	         return cronTrigger.nextExecutionTime(triggerContext);
	     }
	    );
	     scheduledTaskRegistrar.addTriggerTask( ()->{
		       //任务逻辑
		    	  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	  System.out.println("每2.0000秒执行一次"+sdf.format(new Date()));
		     }, triggerContext -> {
		    	 // 任务触发，在这里可修改任务的执行周期,因为每次调度都会执行这里
		         CronTrigger cronTrigger = new CronTrigger("0/2 * * * * ?");
		         //返回执行周期
		         return cronTrigger.nextExecutionTime(triggerContext);
		     }
		    );
	}
	 
    //配置类 
    @Bean(name = "myThreadPoolTaskScheduler")
    public  TaskScheduler getMyThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("Haina-Scheduled-");
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskScheduler.setAwaitTerminationSeconds(60);
        return taskScheduler;
    }      

}
