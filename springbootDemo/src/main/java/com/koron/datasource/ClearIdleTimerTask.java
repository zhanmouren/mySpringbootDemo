package com.koron.datasource;

import java.util.TimerTask;


/**
 * 清除空闲连接任务。
 * 
 * @version 2018年2月26日
 */
public class ClearIdleTimerTask extends TimerTask {
	
	@Override
	public void run() {
		DDSHolder.instance().clearIdleDDS();
	}
}