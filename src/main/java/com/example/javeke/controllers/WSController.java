package com.example.javeke.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("date")
public class WSController {
    @GetMapping
    public String getDate(){
        Date now = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eee MMM d HH:mm:ss ZZ yyyy");

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());

        return zonedDateTime.format(formatter);
    }
}
