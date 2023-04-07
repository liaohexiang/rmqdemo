package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RMQDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RMQDemoApplication.class, args);
	}

}
