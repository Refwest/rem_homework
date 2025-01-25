package com.homework.rem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:db.properties")
@SpringBootApplication
public class RemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemApplication.class, args);
	}

}
