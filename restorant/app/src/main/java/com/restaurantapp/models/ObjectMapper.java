package com.restaurantapp.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper {

    public static Long getGeneratedId(Connection con) throws SQLException {
        ResultSet rs = con.prepareStatement("SELECT * FROM coffee_machine_history").executeQuery();
        long anInt = 0L;
        while (rs.next()) anInt = rs.getLong(1);
        rs.close();
        return anInt;
    }
}
