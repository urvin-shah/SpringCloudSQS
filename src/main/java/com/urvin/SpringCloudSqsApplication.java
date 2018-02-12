package com.urvin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringCloudSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSqsApplication.class, args);
	}
}
