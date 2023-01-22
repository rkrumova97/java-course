package com.restaurantapp.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.restaurantapp.models.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurant where restaurant.id = :id")
    public Restaurant readRestaurant(Long id) throws Exception;

    @Query("SELECT * FROM restaurant")
    public List<Restaurant> readAllRestaurant() throws Exception;

    @Insert
    void updateRestaurant(Restaurant restaurant);
}
