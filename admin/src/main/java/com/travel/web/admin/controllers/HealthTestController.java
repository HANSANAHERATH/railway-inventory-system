package com.travel.web.admin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/client/v1")
public class HealthTestController {
	
	@RequestMapping(value = "/servicecheck", method = RequestMethod.GET)
	public String serviceCheck() {
		System.out.println("ddd");
		return "Client app service check";
	}

}
