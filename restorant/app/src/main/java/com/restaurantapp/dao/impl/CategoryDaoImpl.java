package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.Order;
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

public class CategoryDaoImpl implements CategoryDao {
    private PreparedStatement ps;
    private Connection con;

    @Override
    public Category readCategory(Long id) throws Exception {
        con = getConnection();
        Category category = new Category();

        try {
            System.out.println("Creating statement...");

            ps = Objects.requireNonNull(con)
                    .prepareStatement("SELECT * FROM category WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                String cat = rs.getString("category");
                category.setCategory(cat);
            }
            rs.close();
        } catch (Exception se) {
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
        return category;
    }

    @Override
    public List<Category> readAllCategories() throws Exception {
        Statement stmt = null;
        con = getConnection();
        List<Category> categories = new ArrayList<>();

        try {
            System.out.println("Creating statement...");
            stmt = con.createStatement();

            String sql = "SELECT * FROM category";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                Long id = rs.getLong("id");
                String text = rs.getString("category");

                categories.add(new Category().id(id).category(text));
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
        return categories;
    }
}
