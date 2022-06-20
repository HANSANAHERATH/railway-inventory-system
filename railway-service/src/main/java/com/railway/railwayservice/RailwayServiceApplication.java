package com.railway.railwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RailwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayServiceApplication.class, args);

	}



}
