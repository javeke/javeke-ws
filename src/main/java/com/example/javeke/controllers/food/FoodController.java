package com.example.javeke.controllers.food;

import com.example.javeke.controllers.food.model.FoodResponse;
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

