package com.example.projecttasc.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WebController {
    @GetMapping(value = {"/","home"})
    public String homepage(){
        return "home";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
