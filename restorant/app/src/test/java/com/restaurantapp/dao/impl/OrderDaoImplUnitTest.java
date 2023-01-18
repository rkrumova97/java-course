package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.OrderDao;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.Order;
import com.restaurantapp.models.User;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDaoImplUnitTest {
    @Test
    public void createOffer_isCorrect() throws Exception {
        User user1 = new User()
                .id(3L)
                .password("something")
                .username("anything");
        Offer offer = new Offer()
                .id(2L)
                .price(10L)
                .text("dsfdsds");
        Order order = new Order()
                .id(1L)
                .user(user1)
                .offer(offer)
                .localDateTime(LocalDateTime.from(ZonedDateTime.now()));
        OrderDao orderDao = new OrderDaoImpl();
        Order order1 = orderDao.createOrder(order);
        assert order1.getUser().getPassword().equals(order.getUser().getPassword());
    }

    @Test
    public void readAll_isCorrect() throws Exception {
        OrderDao orderDao = new OrderDaoImpl();
        assert !orderDao.readAllOrder().isEmpty();
    }

    @Test
    public void readOne_isCorrect() throws Exception {
        OrderDao orderDao = new OrderDaoImpl();
        User user1 = new User()
                .id(3L)
                .password("something")
                .username("anything");
        assert orderDao.readOrder(1L).getUser().getPassword().equals(user1.getPassword());
    }

    @Test
    public void delete_isCorrect() throws Exception {

        OrderDao orderDao = new OrderDaoImpl();
        List<Order> offers = orderDao.readAllOrder();
        orderDao.deleteOrder(2L);
        assert offers.size() != orderDao.readAllOrder().size();
    }

}
