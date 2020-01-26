package com.restaurantapp.dao;

import com.restaurantapp.models.Category;

import java.util.List;

public interface CategoryDao {
    Category readCategory(Long id) throws Exception;

    List<Category> readAllCategories() throws Exception;
}
