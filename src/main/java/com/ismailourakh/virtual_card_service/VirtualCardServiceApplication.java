package com.ismailourakh.virtual_card_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class VirtualCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualCardServiceApplication.class, args);
	}
}
