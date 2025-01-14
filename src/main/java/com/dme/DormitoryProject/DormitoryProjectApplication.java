package com.dme.DormitoryProject;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableRabbit
public class DormitoryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DormitoryProjectApplication.class, args);
	}

}
