package com.restaurantapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters(OfferConverter.class)
public class Offer {
    @PrimaryKey
    private Long id;

    @ColumnInfo
    private String text;

    @ColumnInfo
    private Long price;

    @ColumnInfo
    private Category category;

    @ColumnInfo
    private Restaurant restaurant;

    @ColumnInfo
    private String title;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offer id(Long id) {
        setId(id);
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Offer text(String text){
        setText(text);
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public  Offer price(Long price){
        setPrice(price);
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Offer category(Category category){
        setCategory(category);
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Offer restaurant(Restaurant restaurant){
        setRestaurant(restaurant);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Offer title(String title){
        setTitle(title);
        return this;
    }
}