package com.restaurantapp.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper {

    public static Long getGeneratedId(Connection con) throws SQLException {
        ResultSet rs = con.prepareStatement("SELECT * FROM public.order").executeQuery();
        long anInt = 0L;
        while (rs.next()) anInt = rs.getLong(1);
        rs.close();
        return anInt;
    }
    public static Long getGeneratedIdOffers(Connection con) throws SQLException {
        ResultSet rs = con.prepareStatement("SELECT * FROM public.offer").executeQuery();
        long anInt = 0L;
        while (rs.next()) anInt = rs.getLong("id");
        rs.close();
        return anInt;
    }
}