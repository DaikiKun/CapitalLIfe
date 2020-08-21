package com.osk.ctli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CtliAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtliAdminApplication.class, args);
	}
	
	@Bean
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}

}
