package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.restaurantapp.configuration.ConnectionManager.getConnection;

public class RestaurantDaoImpl implements RestaurantDao {
    private Connection con;
    private PreparedStatement ps;
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public Restaurant readRestaurant(Long id) throws Exception {
        con = getConnection();
        Restaurant restaurant = new Restaurant();

        try {
            System.out.println("Creating statement...");

            ps = con
                    .prepareStatement("SELECT * FROM restaurant WHERE id = ? AND is_deleted = false");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");

                restaurant = Restaurant.builder()
                        .id(id)
                        .name(name)
                        .address(address)
                        .build();
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (ps != null)
                    con.close();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return restaurant;
    }

    @Override
    public List<Restaurant> readAllRestaurant() throws Exception {
        Statement stmt = null;
        con = getConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try {
            System.out.println("Creating statement...");
            stmt = con.createStatement();

            String sql = "SELECT * FROM restaurant where is_deleted = false";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                List<Category> categories = getCategories(id);

                restaurants.add(Restaurant.builder()
                        .id(id)
                        .address(address)
                        .name(name)
                        .categories(categories)
                        .build());
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    con.close();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return restaurants;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant, String changedAttribute, Object changeValue) {
        try {

            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            ps = con.prepareStatement("UPDATE restaurant " +
                    " SET "+ changedAttribute + " = ? WHERE id = ? ");
            ps.setObject(1, changeValue);
            ps.setLong(2, restaurant.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (ps != null)
                    Objects.requireNonNull(con).close();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    private List<Category> getCategories(Long restaurant) throws Exception {
        List<Category> categories = new ArrayList<>();
        PreparedStatement preparedStatement = con.prepareStatement("select * from restaurant_category where restaurant_id = ?");
        preparedStatement.setLong(1, restaurant);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long categoryId = resultSet.getLong("category_id");
            Category category = categoryDao.readCategory(categoryId);
            categories.add(category);
        }
        return categories;
    }
}
