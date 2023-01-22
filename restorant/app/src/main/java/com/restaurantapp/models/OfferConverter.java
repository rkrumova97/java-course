package com.restaurantapp.models;

import androidx.room.TypeConverter;

import com.restaurantapp.configuration.ConnectionManager;

public class OfferConverter {
    private static ConnectionManager connectionManager;

    public OfferConverter(ConnectionManager connectionManager){
        OfferConverter.connectionManager = connectionManager;
    }


    @TypeConverter
    public static Long fromCategory(Category category) {
        return category.getId();
    }

    @TypeConverter
    public static Category toCategory(Long value) throws Exception {
        // You should retrieve the Category object from your data source using the id
        return connectionManager.categoryDao().readCategory(value);
    }

    @TypeConverter
    public static Long fromRestaurant(Restaurant restaurant) {
        return restaurant.getId();
    }

    @TypeConverter
    public static Restaurant toRestaurant(Long value) throws Exception {
        // You should retrieve the Restaurant object from your data source using the id
        return connectionManager.restaurantDao().readRestaurant(value);
    }
}
