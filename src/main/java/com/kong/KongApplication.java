package com.kong;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableRabbit
@ServletComponentScan(basePackages = {"com.kong"})
@MapperScan(basePackages = {"com.kong.dao"})
public class KongApplication {

	public static void main(String[] args) {
		SpringApplication.run(KongApplication.class, args);
	}

}


