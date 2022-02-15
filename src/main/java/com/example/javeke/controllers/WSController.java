package com.example.javeke.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("date")
public class WSController {
    @GetMapping
    public String getDate(){
        Date now = new Date(System.currentTimeMillis());
        return now.toString();
    }
}
