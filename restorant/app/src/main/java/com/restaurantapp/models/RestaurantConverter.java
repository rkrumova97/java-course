package com.restaurantapp.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RestaurantConverter {
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
}
