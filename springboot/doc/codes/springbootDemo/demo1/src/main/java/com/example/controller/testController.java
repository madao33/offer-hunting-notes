package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest模式
@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping
    public String getById() {
        System.out.println("SpringBoot is running...1 testing");
        return "springboot is running...1 testing";
    }
}