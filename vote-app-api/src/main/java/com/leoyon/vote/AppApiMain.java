package com.leoyon.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootConfiguration
@SpringBootApplication(scanBasePackages = {
})
public class AppApiMain {

	public static void main(String[] args) {
		SpringApplication.run(AppApiMain.class, args);
	}

}
