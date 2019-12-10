package com.restaurantapp.dao;

import com.restaurantapp.models.Category;

public interface CategoryDao {
    Category readCategory(Long id);
}
