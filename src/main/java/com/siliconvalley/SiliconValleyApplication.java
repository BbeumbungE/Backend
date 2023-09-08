package com.siliconvalley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SiliconValleyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiliconValleyApplication.class, args);
	}

}
