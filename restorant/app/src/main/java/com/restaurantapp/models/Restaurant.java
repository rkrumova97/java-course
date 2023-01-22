package com.restaurantapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
@TypeConverters(RestaurantConverter.class)
public class Restaurant {
    @PrimaryKey
    private Long id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String address;

    @ColumnInfo
    private List<Category> categories;

    public Restaurant() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Restaurant id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant name(String name){
        setName(name);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant address(String address){
        setAddress(address);
        return this;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Restaurant categories(List<Category> categories) {
        setCategories(categories);
        return this;
    }
}
