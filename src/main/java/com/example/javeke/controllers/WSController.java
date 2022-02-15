package com.example.javeke.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("date")
public class WSController {
    @GetMapping
    public String getDate(){
        ZonedDateTime zone = ZonedDateTime.now(ZoneId.of("UTC"));
        return zone.format(DateTimeFormatter.ofPattern("eee MMM d HH:mm:ss z yyyy"));
    }
}
