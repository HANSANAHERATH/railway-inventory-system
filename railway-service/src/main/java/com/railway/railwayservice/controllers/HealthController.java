package com.railway.railwayservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(path = "/service-check")
    public String serviceCheck(){
        return "This is a service check on railway system";
    }
}
