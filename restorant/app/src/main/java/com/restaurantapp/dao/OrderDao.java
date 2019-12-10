package com.restaurantapp.dao;

import com.restaurantapp.models.Order;

import java.util.List;

public interface OrderDao {
    public Order createOrder(Order Order);

    public Order readOrder(Long id);

    public List<Order> readAllOrder();

    public Order updateOrder(Order Order);

    public void deleteOrder(Long id);
}
