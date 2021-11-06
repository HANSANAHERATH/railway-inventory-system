package com.travel.web.client.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/v1")
public class HealthTestController {
	
	private static Logger logger = LoggerFactory.getLogger(HealthTestController.class);
	
	
	@RequestMapping(value = "/servicecheck", method = RequestMethod.GET)
	public String serviceCheck() {
		logger.info("Inside service Check");
		return "Client app service check";
	}

}
