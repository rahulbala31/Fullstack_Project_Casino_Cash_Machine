package com.example.cashmachinebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "com.example.cashmachinebackend.model")
public class CashmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashmachineApplication.class, args);
	}

}
