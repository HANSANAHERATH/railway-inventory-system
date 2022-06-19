package com.railway.railwayservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Health controller.
 */
@RestController
public class HealthController {

    /**
     * Service check string.
     *
     * @return the string
     */
    @GetMapping(path = "/service-check")
    public String serviceCheck(){
        return "This is a service check on railway system";
    }
}
