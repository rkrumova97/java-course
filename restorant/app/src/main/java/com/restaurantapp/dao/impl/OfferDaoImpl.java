package com.restaurantapp.dao.impl;

import com.restaurantapp.configuration.ConnectionManager;
import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.ObjectMapper;
import com.restaurantapp.models.Offer;
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

public class OfferDaoImpl implements OfferDao {
    private Connection con;
    private PreparedStatement ps;
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private RestaurantDao restaurantDao = new RestaurantDaoImpl();

    @Override
    public Offer createOffer(Offer offer) throws Exception {
        try {
            con = getConnection();
            //TODO make the id autoincrement
            ps = Objects.requireNonNull(con)
                    .prepareStatement("INSERT INTO offer (text, category, price, restaurant) VALUES(?, ?, ?, ?)");

            ps.setString(1, offer.getText());
            ps.setLong(2, offer.getCategory().getId());
            ps.setLong(3, offer.getPrice());
            ps.setLong(4, offer.getRestaurant().getId());

            ps.executeUpdate();

            offer.setId(ObjectMapper.getGeneratedIdOffers(con));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }

        return offer;
    }

    @Override
    public Offer readOffer(Long id) throws Exception {
        con = getConnection();
        Offer offer = new Offer();

        try {
            System.out.println("Creating statement...");

            ps = con
                    .prepareStatement("SELECT * FROM offer WHERE id = ? AND is_deleted = false");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                Long price = rs.getLong("price");
                String text = rs.getString("text");
                Long restaurantId = rs.getLong("restaurant");
                Restaurant restaurant = restaurantDao.readRestaurant(restaurantId);
                Long categoryId = rs.getLong("category");
                Category category = categoryDao.readCategory(categoryId);

                offer = Offer.builder()
                        .id(id)
                        .price(price)
                        .restaurant(restaurant)
                        .category(category)
                        .text(text).build();
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
        return offer;
    }

    @Override
    public List<Offer> readAllOffer() throws Exception {
        Statement stmt = null;
        con = getConnection();
        List<Offer> offers = new ArrayList<>();

        try {
            System.out.println("Creating statement...");
            stmt = con.createStatement();

            String sql = "SELECT * FROM offer where is_deleted = false";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                Long price = rs.getLong("price");
                Long id = rs.getLong("id");
                String text = rs.getString("text");
                Long restaurantId = rs.getLong("restaurant");
                Restaurant restaurant = restaurantDao.readRestaurant(restaurantId);
                Long categoryId = rs.getLong("category");
                Category category = categoryDao.readCategory(categoryId);

                offers.add(Offer.builder()
                        .id(id)
                        .price(price)
                        .restaurant(restaurant)
                        .category(category)
                        .text(text).build());
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
        return offers;
    }

    @Override
    public void updateOffer(Offer offer) {
        try {

            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            ps = con.prepareStatement("UPDATE offer " +
                    "SET (title,  text, price, category) = (?,?,?,?) WHERE id = ?");
            ps.setString(1, offer.getTitle());
            ps.setString(2, offer.getText());
            ps.setLong(3, offer.getPrice());
            ps.setLong(4, offer.getCategory().getId());
            ps.setLong(5, offer.getId());
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

    @Override
    public void deleteOffer(Long id) {
        try {
            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            ps = con.prepareStatement("UPDATE  offer SET is_deleted = true " +
                    "WHERE id = ?");
            ps.setLong(1, id);
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
}
