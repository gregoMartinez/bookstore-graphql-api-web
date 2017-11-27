package com.bookstore.api.web.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories(basePackages = "com.bookstore.api.web.persistence.repository")
@ComponentScan(basePackages = "com.bookstore.api.web")
@SpringBootApplication
public class SpringBootGraphqlApplication{


	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphqlApplication.class, args);
	}
}
