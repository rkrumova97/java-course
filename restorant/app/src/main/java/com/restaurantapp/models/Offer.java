package com.restaurantapp.models;

public class Offer {
    private Long id;

    private String text;

    private Long price;

    private Category category;

    private Restaurant restaurant;

    private String title;

    public Offer() {
    }

    public Offer(Long id, String text, Long price, Category category, Restaurant restaurant, String title) {
        this.id = id;
        this.text = text;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
        this.title = title;
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