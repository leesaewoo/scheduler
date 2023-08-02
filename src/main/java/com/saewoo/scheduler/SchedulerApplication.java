package com.saewoo.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SchedulerApplication extends SpringBootServletInitializer { //상속 추가됨

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

	@Override // 추가됨
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SchedulerApplication.class);
	}
}
