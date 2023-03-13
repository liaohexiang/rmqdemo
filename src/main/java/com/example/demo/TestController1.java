package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class TestController1 {
    //@GetMapping("/hello")
    public String hello(){
        return "hello1";
    }
}
