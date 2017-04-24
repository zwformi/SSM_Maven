package com.zw.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {
	private static Logger logger = Logger.getLogger(Timer.class);
	@Scheduled(cron = "* 0/1 * * * *")
	public void clientInfoTimer() {
		System.out.println("---------i am coming!!------------");
		logger.info("---------i am coming!!------------");
	}
}
