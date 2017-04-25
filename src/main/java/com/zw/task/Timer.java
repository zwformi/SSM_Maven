package com.zw.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {
	private static Logger logger = Logger.getLogger(Timer.class);
	@Scheduled(cron = "* 0/50 * * * *")
	public void clientInfoTimer() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		logger.info("---------i am coming!!------------");
	}
}
