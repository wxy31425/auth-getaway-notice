package com.auth.get.away.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthGetawayNoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthGetawayNoticeApplication.class, args);
	}

}