package com.uaq.schedulers;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class TestScheduler {
	
	@Scheduled(cron = "*/5 * * * * ?")
	public void run(){
		System.out.println("test");
		System.out.println(new Date().toString());
	}

}
