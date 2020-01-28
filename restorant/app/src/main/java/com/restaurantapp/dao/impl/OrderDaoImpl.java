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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.restaurantapp.configuration.ConnectionManager.getConnection;

public class OrderDaoImpl implements OrderDao {
    private Connection con;
    private PreparedStatement ps;
    private UserDao userDao = new UserDaoImpl();
    private OfferDao offerDao = new OfferDaoImpl();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Order createOrder(Order order) throws Exception {
        try {
            con = getConnection();
            ps = Objects.requireNonNull(con)
                    .prepareStatement("INSERT INTO public.order ( offer, person, time) VALUES( ?, ?, ?)");

          //  ps.setTimestamp(1, Timestamp.valueOf(order.getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss[.xxxxx]"))));
            ps.setLong(1, order.getOffer().getId());
            ps.setLong(2, order.getUser().getId());
            ps.setTimestamp(3,  new Timestamp(((order.getLocalDateTime()).atZone( ZoneId.systemDefault()).toInstant().toEpochMilli())));

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
                    .prepareStatement("SELECT * FROM public.order WHERE id = ? AND is_deleted = false");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {

                //ZonedDateTime time = ZonedDateTime.parse(rs.getTimestamp("time").toString());
                Long offerId = rs.getLong("offer");
                Offer offer = offerDao.readOffer(offerId);
                Long userId = rs.getLong("person");
                User user = userDao.readUser(userId);

                order = Order.builder()
                        .id(id)
                        .localDateTime(LocalDateTime.now())
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

            String sql = "SELECT * FROM public.order where is_deleted = false";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                Long id = rs.getLong("id");
                //ZonedDateTime time = ZonedDateTime.parse(rs.getTimestamp("time").toString());
                Long offerId = rs.getLong("offer");
                Offer offer = offerDao.readOffer(offerId);
                Long userId = rs.getLong("person");
                User user = userDao.readUser(userId);

                orders.add(Order.builder()
                        .id(id)
                        .localDateTime(LocalDateTime.now())
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
            ps = con.prepareStatement("UPDATE public.order " +
                    "SET "+changedAttribute+" = ? WHERE id = ?");

            ps.setObject(1, changeValue);
            ps.setObject(2, order.getId());
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

            ps = con.prepareStatement("UPDATE  public.order SET is_deleted = true " +
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
