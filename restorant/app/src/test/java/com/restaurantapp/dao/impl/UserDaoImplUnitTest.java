package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Restaurant;
import com.restaurantapp.models.Role;
import com.restaurantapp.models.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImplUnitTest {
    @Test
    public void createUser_isCorrect() throws Exception {
        User user1 = User.builder()
                .id(3L)
                .password("something")
                .username("anything")
                .token("")
                .username("")
                .role(Role.EMPLOYEE)
                .phoneNumber("")
                .email("")
                .lastName("")
                .firstName("")
                .address("")
                .restaurant(new Restaurant(1L, "", "", new ArrayList<>()))
                .build();
        UserDao userDao = new UserDaoImpl();
        User user = userDao.createUser(user1);
        assert user.getPassword().equals(user1.getPassword());
    }

    @Test
    public void readAll_isCorrect() throws Exception {
        UserDao userDao = new UserDaoImpl();
        assert !userDao.readAllUser().isEmpty();
    }

    @Test
    public void readOne_isCorrect() throws Exception {
        UserDao userDao = new UserDaoImpl();
        assert userDao.readUser(1L).getPassword().equals("something");
    }


    @Test
    public void update_isCorrect() throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = User.builder()
                .id(1L)
                .address("")
                .build();
        userDao.updateUser(user);
        assert userDao.readUser(1L).getAddress().equals("Rest");

    }

    @Test
    public void delete_isCorrect() throws Exception {

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.readAllUser();
        userDao.deleteUser(2L);
        assert users.size() != userDao.readAllUser().size();

    }

}
