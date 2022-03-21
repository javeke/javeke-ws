package com.example.javeke.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {

    @GetMapping("/")
    public String welcome(){
        return "Welcome to Jamrock";
    }
}
