package com.restaurantapp.dao;

import com.restaurantapp.models.Order;

import java.util.List;

public interface OrderDao {
    public Order createOrder(Order Order) throws Exception;

    public Order readOrder(Long id) throws Exception;

    public List<Order> readAllOrder() throws Exception;


    Order updateOrder(Order order, String changedAttribute, Object changeValue);

    public void deleteOrder(Long id);
}
