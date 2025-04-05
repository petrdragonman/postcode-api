package com.petr.postcode_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.petr.postcode_api")
public class PostcodeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostcodeApiApplication.class, args);
	}

}
