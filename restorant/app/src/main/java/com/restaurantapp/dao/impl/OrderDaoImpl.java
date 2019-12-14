package com.restaurantapp.dao.impl;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.restaurantapp.configuration.ConnectionManager;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.OrderDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.ObjectMapper;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.Order;
import com.restaurantapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.restaurantapp.configuration.ConnectionManager.getConnection;

public class OrderDaoImpl implements OrderDao {
    private Connection con;
    private PreparedStatement ps;
    private UserDao userDao = new UserDaoImpl();
    private OfferDao offerDao = new OfferDaoImpl();

    @Override
    public Order createOrder(Order order) throws Exception {
        try {
            con = getConnection();
            ps = Objects.requireNonNull(con)
                    .prepareStatement("INSERT INTO order (time, offer, user) VALUES(?, ?, ?)");

            ps.setObject(1, order.getLocalDateTime());
            ps.setLong(2, order.getOffer().getId());
            ps.setLong(3, order.getUser().getId());

            ps.executeUpdate();

            order.setId(ObjectMapper.getGeneratedId(con));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }

        return order;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Order readOrder(Long id) throws Exception {
        con = getConnection();
        Order order = new Order();

        try {
            System.out.println("Creating statement...");

            ps = con
                    .prepareStatement("SELECT * FROM order WHERE id = ? AND is_deleted = false");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {

                LocalDateTime time = LocalDateTime.parse(rs.getTimestamp("time").toString());
                Long offerId = rs.getLong("restaurant");
                Offer offer = offerDao.readOffer(offerId);
                Long userId = rs.getLong("category");
                User user = userDao.readUser(userId);

                order = Order.builder()
                        .id(id)
                        .localDateTime(time)
                        .offer(offer)
                        .user(user)
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
        return order;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Order> readAllOrder() throws Exception {
        Statement stmt = null;
        con = getConnection();
        List<Order> orders = new ArrayList<>();

        try {
            System.out.println("Creating statement...");
            stmt = con.createStatement();

            String sql = "SELECT * FROM order where is_deleted = false";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                Long id = rs.getLong("id");
                LocalDateTime time = LocalDateTime.parse(rs.getTimestamp("time").toString());
                Long offerId = rs.getLong("restaurant");
                Offer offer = offerDao.readOffer(offerId);
                Long userId = rs.getLong("category");
                User user = userDao.readUser(userId);

                orders.add(Order.builder()
                        .id(id)
                        .localDateTime(time)
                        .offer(offer)
                        .user(user)
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
        return orders;
    }

    @Override
    public Order updateOrder(Order order, String changedAttribute, Object changeValue) {
        try {

            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            ps = con.prepareStatement("UPDATE order " +
                    "SET ? = ? WHERE id = ?");
            ps.setString(1, changedAttribute);
            ps.setObject(2, changeValue);
            ps.setObject(3, order.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
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
        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        try {
            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            ps = con.prepareStatement("UPDATE  order SET is_deleted = true " +
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
