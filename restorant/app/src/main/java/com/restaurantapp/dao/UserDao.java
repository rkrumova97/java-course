package com.restaurantapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.restaurantapp.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void createUser(User User) throws Exception;

    @Query("SELECT * FROM user where user.id = :id")
    User readUser(String id) throws Exception;

    @Query("SELECT * FROM user where user.id = :id")
    User readUser(Long id) throws Exception;

    @Query("SELECT * FROM user")
    List<User> readAllUser() throws Exception;

}
