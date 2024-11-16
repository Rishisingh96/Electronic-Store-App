package com.rishi.electronic.store.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class HomeController {

    @GetMapping
    public String testing(){
        return "Welcome to electronic store";
    }
}
