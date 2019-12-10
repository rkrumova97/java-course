package com.restaurantapp.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static Connection con;

    public static Connection getConnection() throws Exception {

        try {

            final String db_driver = "org.postgresql.Driver";
            final String url = "jdbc:postgresql://localhost:5433/restaurant";
            final String user = "postgres";
            final String password = "postgres";
            Class.forName(db_driver); //Driver loading

            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection con) throws Exception {
        try {
            con.close();
            System.out.println("Connection was closed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
