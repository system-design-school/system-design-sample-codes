package com.example.service.search.controller;

import com.example.entity.Restaurant;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    ElasticsearchRestTemplate restTemplate;

    @GetMapping
    public List<Restaurant> search(String keyword, Double latitude, Double longitude, Double distance) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryOuterBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQueryInnerBuilder = QueryBuilders.boolQuery();
        boolQueryInnerBuilder.should(QueryBuilders.matchQuery("name", keyword));
        boolQueryInnerBuilder.should(QueryBuilders.matchQuery("speciality", keyword));
        boolQueryOuterBuilder.must(boolQueryInnerBuilder);
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = new GeoDistanceQueryBuilder("geoPoint");
        geoDistanceQueryBuilder.point(latitude, longitude);
        geoDistanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        boolQueryOuterBuilder.must(geoDistanceQueryBuilder);
        nativeSearchQueryBuilder.withQuery(boolQueryOuterBuilder);
        SearchHits<Restaurant> search = restTemplate.search(nativeSearchQueryBuilder.build(), Restaurant.class);
        return search.stream().map(e -> e.getContent()).collect(Collectors.toList());
    }
}
