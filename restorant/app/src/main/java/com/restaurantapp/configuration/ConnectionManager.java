package com.restaurantapp.configuration;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public static Connection getConnection() throws Exception {

        try {

            final String db_driver = "org.postgresql.Driver";
            final String url = "jdbc:postgresql://10.0.2.2:5432/restaurant";
            final String user = "postgres";
            final String password = "Erasmusstud1";
            Class.forName(db_driver); //Driver loading

            Connection con = DriverManager.getConnection(url, user, password);

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
