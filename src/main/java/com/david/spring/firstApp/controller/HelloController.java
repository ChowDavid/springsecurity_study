package com.david.spring.firstApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Spring security Rocks!!";
    }
    @GetMapping("/bye")
    public String bye(){
        return "Bye Bye!!";
    }
}
