package com.restaurantapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    private Long id;

    @ColumnInfo
    private String category;

    public Category() {
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
