package com.restaurantapp.models;

import androidx.room.TypeConverter;

import com.restaurantapp.configuration.ConnectionManager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class OrderConverter {
    private static ConnectionManager connectionManager;

    public OrderConverter(ConnectionManager connectionManager){
        OrderConverter.connectionManager = connectionManager;
    }


    @TypeConverter
    public static Long fromUser(User user) {
        return user.getId();
    }

    @TypeConverter
    public static User toUser(Long value) throws Exception {
        return connectionManager.userDao().readUser(value);
    }

    @TypeConverter
    public static Long fromOffer(Offer offer) {
        return offer.getId();
    }
    @TypeConverter
    public static Offer toOffer(Long value) throws Exception {
        return connectionManager.offerDao().readOffer(value);
    }

    @TypeConverter
    public static Long fromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(Long value) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault());
    }
}
