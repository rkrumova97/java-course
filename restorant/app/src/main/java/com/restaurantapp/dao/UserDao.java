package com.restaurantapp.dao;

import com.restaurantapp.models.User;

import java.util.List;

public interface UserDao {
    public User createUser(User User) throws Exception;

    public User readUser(String id) throws Exception;

    User readUser(Long id) throws Exception;

    public List<User> readAllUser() throws Exception;

    void updateUser(User user);

    public void deleteUser(Long id);

}
