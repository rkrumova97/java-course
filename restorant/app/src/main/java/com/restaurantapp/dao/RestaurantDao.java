package com.restaurantapp.dao;


import com.restaurantapp.models.Restaurant;

import java.util.List;

public interface RestaurantDao {
    public Restaurant readRestaurant(Long id) throws Exception;

    public List<Restaurant> readAllRestaurant() throws Exception;

    void updateRestaurant(Restaurant restaurant, String changedAttribute, Object changeValue);
}
