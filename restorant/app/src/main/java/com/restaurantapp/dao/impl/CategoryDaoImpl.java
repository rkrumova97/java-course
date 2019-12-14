package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.restaurantapp.configuration.ConnectionManager.getConnection;

public class CategoryDaoImpl implements CategoryDao {
    private PreparedStatement ps;

    @Override
    public Category readCategory(Long id) throws Exception {
        Connection con = getConnection();
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
}
