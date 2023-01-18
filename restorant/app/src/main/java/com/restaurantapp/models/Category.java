package com.restaurantapp.models;

public class Category {
    private Long id;

    private String category;

    public Category() {
    }

    public Category(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category id(Long id){
        setId(id);
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category category(String category){
        setCategory(category);
        return this;
    }
}
