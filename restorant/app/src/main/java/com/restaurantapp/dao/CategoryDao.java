package com.restaurantapp.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.restaurantapp.models.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category WHERE category.id = :id")
    Category readCategory(Long id) throws Exception;

    @Query("SELECT * FROM category")
    List<Category> readAllCategories() throws Exception;
}
