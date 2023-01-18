package com.restaurantapp.dao.impl;

import static com.restaurantapp.configuration.ConnectionManager.getConnection;

import com.restaurantapp.configuration.ConnectionManager;
import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.ObjectMapper;
import com.restaurantapp.models.Restaurant;
import com.restaurantapp.models.Role;
import com.restaurantapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao {
    private Connection con;
    private PreparedStatement ps;
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private RestaurantDao restaurantDao = new RestaurantDaoImpl();

    @Override
    public User createUser(User user) throws Exception {
        try {
            con = getConnection();
            ps = Objects.requireNonNull(con)
                    .prepareStatement("INSERT INTO public.user (address, email, first_name, last_name" +
                            ", password, phone_number, token, username, role, restaurant,id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, user.getAddress());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getToken());
            ps.setString(8, user.getUsername());
            ps.setString(9, user.getRole().getValue());
            ps.setLong(10, user.getRestaurant().getId());
            ps.setLong(11,user.getId());

            ps.executeUpdate();

            Long generatedId = ObjectMapper.getGeneratedId(con);
            user.setId(generatedId);

            ps.close();

            ps = con.prepareStatement("INSERT INTO user_category (user_id, category_id) VALUES(?, ?)");
            for(Category category : user.getCategories()){
                ps.setLong(1, user.getId());
                ps.setLong(2, category.getId());
                ps.executeUpdate();
            }
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }

        return user;
    }

    @Override
    public User readUser(String email) throws Exception {
        con = getConnection();
        User user = new User();

        try {
            System.out.println("Creating statement...");

            ps = con
                    .prepareStatement("SELECT * FROM public.user WHERE email = ? AND is_deleted = false");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Long id = rs.getLong("id");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String roleValue = rs.getString("role");
                Role role = Role.findByValue(roleValue);
                String password = rs.getString("password");
                String username = rs.getString("username");
                Long restaurantId = rs.getLong("restaurant");
                String token = rs.getString("token");
                Restaurant restaurant = restaurantDao.readRestaurant(restaurantId);
                List<Category> categories = getCategories(id);

                user = new User()
                        .id(id)
                        .firstName(firstName)
                        .address(address)
                        .categories(categories)
                        .restaurant(restaurant)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .phoneNumber(phoneNumber)
                        .role(role)
                        .username(username)
                        .token(token);
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
        return user;

    }
    @Override
    public User readUser(Long id) throws Exception {
        con = getConnection();
        User user = new User();

        try {
            System.out.println("Creating statement...");

            ps = con
                    .prepareStatement("SELECT * FROM public.user WHERE id = ? AND is_deleted = false");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String roleValue = rs.getString("role");
                Role role = Role.findByValue(roleValue);
                String password = rs.getString("password");
                String username = rs.getString("username");
                Long restaurantId = rs.getLong("restaurant");
                String token = rs.getString("token");
                Restaurant restaurant = restaurantDao.readRestaurant(restaurantId);
                List<Category> categories = getCategories(id);

                user = new User()
                        .id(id)
                        .firstName(firstName)
                        .address(address)
                        .categories(categories)
                        .restaurant(restaurant)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .phoneNumber(phoneNumber)
                        .role(role)
                        .username(username)
                        .token(token);
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
        return user;

    }

    @Override
    public List<User> readAllUser() throws Exception {
        Statement stmt = null;
        con = getConnection();
        List<User> users = new ArrayList<>();

        try {
            System.out.println("Creating statement...");
            stmt = con.createStatement();

            String sql = "SELECT * FROM public.user where is_deleted = false";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String roleValue = rs.getString("role");
                Role role = Role.findByValue(roleValue);
                String password = rs.getString("password");
                String username = rs.getString("username");
                Long restaurantId = rs.getLong("restaurant");
                String token = rs.getString("token");
                //Restaurant restaurant = restaurantDao.readRestaurant(restaurantId);
                //List<Category> categories = getCategories(id);

                users.add(new User()
                        .id(id)
                        .firstName(firstName)
                        .address(address)
                        //.categories(categories)
                        //.restaurant(restaurant)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .phoneNumber(phoneNumber)
                        .role(role)
                        .username(username)
                        .token(token));

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
        return users;    }

    @Override
    public void updateUser(User user) {
        try {

            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            ps = con.prepareStatement("UPDATE public.user " +
                    "SET (first_name,last_name,email) = (?,?,?) WHERE id = ?");

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getId());
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
    public void deleteUser(Long id) {
        try {
            con = getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            ps = con.prepareStatement("UPDATE  public.user SET is_deleted = true " +
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

    private List<Category> getCategories(Long id) throws Exception {
        List<Category> categories = new ArrayList<>();
        PreparedStatement preparedStatement = con.prepareStatement("select * from user_category where user_id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long categoryId = resultSet.getLong("category_id");
            Category category = categoryDao.readCategory(categoryId);
            categories.add(category);
        }
        return categories;
    }

}
