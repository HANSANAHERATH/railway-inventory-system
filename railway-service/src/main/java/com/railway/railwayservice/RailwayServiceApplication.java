package com.railway.railwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableCaching
public class RailwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayServiceApplication.class, args);

	}



}
