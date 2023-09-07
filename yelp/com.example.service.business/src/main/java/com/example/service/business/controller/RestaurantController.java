package com.example.service.business.controller;

import com.alibaba.fastjson.JSON;
import com.example.entity.Restaurant;
import com.example.service.business.dao.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
    @Autowired
    RestaurantDao restaurantDao;

    @GetMapping("/{id}")
    public String info(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantDao.findById(id);
        return restaurant.isEmpty() ? "{id:-1, name:'not found', speciality:''}" : JSON.toJSONString(restaurant.get());
    }
}
