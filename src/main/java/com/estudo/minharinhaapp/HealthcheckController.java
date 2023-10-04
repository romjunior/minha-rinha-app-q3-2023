package com.estudo.minharinhaapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HealthcheckController {

    @GetMapping
    public String healthcheck(){
        return "Everything is OK";
    }
}
