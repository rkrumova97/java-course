package com.restaurantapp.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restaurantapp.configuration.ConnectionManager;

import java.lang.reflect.Type;
import java.util.List;

public class UserConverter {
    private static ConnectionManager connectionManager;

    public UserConverter(ConnectionManager connectionManager){
        UserConverter.connectionManager = connectionManager;
    }

    @TypeConverter
    public static String fromCategoryList(List<Category> categories) {
        Gson gson = new Gson();
        return gson.toJson(categories);
    }
    @TypeConverter
    public static List<Category> toCategoryList(String value) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Category>>(){}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromRole(Role role) {
        return role.name();
    }
    @TypeConverter
    public static Role toRole(String value) {
        return Role.valueOf(value);
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