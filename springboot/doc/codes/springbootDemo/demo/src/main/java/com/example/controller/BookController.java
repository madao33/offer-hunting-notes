package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @GetMapping
    public String getById() {
        System.out.println("SpringBoot is running...");
        return "SpringBoot is running...";
    }
}
