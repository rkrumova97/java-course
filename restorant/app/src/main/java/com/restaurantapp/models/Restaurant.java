package com.restaurantapp.models;

import java.util.List;

public class Restaurant {
    private Long id;

    private String name;

    private String address;

    private List<Category> categories;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.categories = categories;
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
