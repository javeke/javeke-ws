package com.example.javeke.ws.food.controllers.food;

import com.example.javeke.ws.food.models.responses.FoodResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {

    @GetMapping
    public FoodResponse getFoods(){
        return new FoodResponse("Plantain", "Fruit", "Jamaica");
    }
}

