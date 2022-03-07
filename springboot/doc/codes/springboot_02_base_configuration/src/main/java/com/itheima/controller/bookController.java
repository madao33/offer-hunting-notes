package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest模式
@RestController
@RequestMapping("/books")
public class bookController {


    // 读取ymal中的单一数据
    @Value("${country}")
    private String country1;

    @Value("${user.name}")
    private String name1;

    @Value("${likes[1]}")
    private String likes1;

    @Value("${users[1].age}")
    private String age1;

    @GetMapping
    public String getById(){
        System.out.println("country1===>" + country1);
        System.out.println("name1===>" + name1);
        System.out.println("likes1===>" + likes1);
        System.out.println("ages1===>" + age1);
        return "country1===>" + country1;
    }

}
