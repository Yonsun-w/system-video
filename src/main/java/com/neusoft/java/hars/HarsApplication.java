package com.neusoft.java.hars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot 启动类
 */
@Configuration
@SpringBootApplication
public class HarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarsApplication.class, args);
	}

}
