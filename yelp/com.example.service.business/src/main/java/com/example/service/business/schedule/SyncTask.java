package com.example.service.business.schedule;

import com.example.entity.Restaurant;
import com.example.service.business.dao.RestaurantDao;
import com.example.service.business.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyncTask {
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    RestaurantRepository restaurantRepository;

    /**
     * sync restaurants data from mysql db to Elasticsearch per min
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void syncToES() {
        System.out.println("sync to elasticsearch");
        List<Restaurant> restaurants = restaurantDao.findAll();
        restaurants.forEach((e) -> e.genGeoPoint());
        restaurantRepository.saveAll(restaurants);
    }
}
