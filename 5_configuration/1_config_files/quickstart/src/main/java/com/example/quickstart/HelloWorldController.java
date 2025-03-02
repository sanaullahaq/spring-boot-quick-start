package com.example.quickstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public String root(){
        return "This is root";
    }

    @GetMapping("/hello")
    public String helloWorld(){ return "Hello World"; }

//    @GetMapping("/hello/{username}")
//    public String helloWorld(@PathVariable String username){
//        return "Hello World"+" "+username;
//    }
}
