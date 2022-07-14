package com.example.javeke.ws.date.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/date")
public class DateController {
    @GetMapping
    public String getDate( @RequestParam(name = "minutesOffset", required = false) Integer minutesOffset){
        Date now = new Date(System.currentTimeMillis() + (1000L * 60 * (minutesOffset == null ? 0 : minutesOffset)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eee MMM d HH:mm:ss ZZ yyyy");

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());

        return zonedDateTime.format(formatter);
    }
}
