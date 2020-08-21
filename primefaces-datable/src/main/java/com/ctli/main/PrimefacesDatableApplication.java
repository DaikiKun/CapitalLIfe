package com.ctli.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.ctli.main"})
@EntityScan("com.ctli.main.model")
@EnableJpaRepositories("com.ctli.main.repository")
@Configuration
public class PrimefacesDatableApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimefacesDatableApplication.class, args);
	}

}
