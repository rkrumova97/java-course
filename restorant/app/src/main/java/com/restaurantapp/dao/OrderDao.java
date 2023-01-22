package com.restaurantapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.restaurantapp.models.Order;

import java.util.List;

@Dao
public
interface OrderDao {
     @Insert
     void createOrder(Order Order) throws Exception;

     @Query("SELECT * FROM `order` where `order`.id = :id")
     Order readOrder(Long id) throws Exception;

     @Query("SELECT * FROM `order`")
    public List<Order> readAllOrder() throws Exception;
}
