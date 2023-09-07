package com.example.service.location.controller;

import com.example.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("nearby")
    /**
     * search restaurants within a radius
     * geoHash: user's location hash
     * radius: location radius, unit km.
     */
    public String nearby(@RequestParam(defaultValue = "") double latitude, @RequestParam double longitude,
                         @RequestParam(defaultValue = "20") int radius) {
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(radius, Metrics.KILOMETERS));
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        geoRadiusCommandArgs.sortAscending();
        geoRadiusCommandArgs.includeDistance();
        geoRadiusCommandArgs.includeCoordinates();
        BoundGeoOperations restaurants = redisTemplate.boundGeoOps("restaurants");
        GeoResults<RedisGeoCommands.GeoLocation> results = restaurants.radius(circle, geoRadiusCommandArgs);
        List<GeoResult<RedisGeoCommands.GeoLocation>> content = results.getContent();
        Stream<String> list = content.stream().map(item -> {
            Restaurant restaurant = getRestaurantById(item.getContent().getName().toString());
            String distance = item.getDistance().toString();
            return restaurant.getId() + "<br/>" + restaurant.getName() + "<br/>" + restaurant.getSpeciality() + "<br/>" + distance + "<br/>";
        });
        return list.collect(Collectors.joining("<br/>"));
    }

    private Restaurant getRestaurantById(String id) {
        ResponseEntity<Restaurant> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8082/restaurant/" + id, Restaurant.class);
        return responseEntity.getBody();
    }
}
