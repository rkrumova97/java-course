package com.restaurantapp.dao;

import com.restaurantapp.models.User;

import java.util.List;

public interface UserDao {
    public User createUser(User User);

    public User readUser(Long id);

    public List<User> readAllUser();

    public User updateUser(User User);

    public void deleteUser(Long id);

}
