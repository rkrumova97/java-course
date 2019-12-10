package com.restaurantapp.dao;


import com.restaurantapp.models.Restaurant;

import java.util.List;

public interface RestaurantDao {
    public Restaurant readRestaurant(Long id);

    public List<Restaurant> readAllRestaurant();

    public Restaurant updateRestaurant(Restaurant Restaurant);

}
