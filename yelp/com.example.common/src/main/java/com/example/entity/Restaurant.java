package com.example.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurant")
@Document(indexName = "restaurant")
public class Restaurant implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String speciality;
    @Column
    @org.springframework.data.annotation.Transient
    @Field(ignoreFields = "longitude")
    private double longitude;
    @Column
    @org.springframework.data.annotation.Transient
    @Field(ignoreFields = "latitude")
    private double latitude;
    @Transient
    @GeoPointField
    private GeoPoint geoPoint;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", latitude='" + geoPoint.getLat() + '\'' +
                ", longitude='" + geoPoint.getLon() + '\'' +
                ", geoPoint=" + geoPoint +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public void genGeoPoint() {
        this.geoPoint = new GeoPoint(latitude, longitude);
    }

    public Restaurant(Long id, String name, String speciality, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geoPoint = new GeoPoint(latitude, longitude);
    }

    public Restaurant() {
    }
}
