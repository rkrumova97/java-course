package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.models.Restaurant;

import org.junit.Test;

public class RestaurantDaoImplUnitTest {
    @Test
    public void readAll_isCorrect() throws Exception {
        RestaurantDao restaurantDao = new RestaurantDaoImpl();
        assert !restaurantDao.readAllRestaurant().isEmpty();
    }

    @Test
    public void readOne_isCorrect() throws Exception {
        RestaurantDao restaurantDao = new RestaurantDaoImpl();
        assert restaurantDao.readRestaurant(1L).getName().equals("Rest");
    }

    @Test
    public void update_isCorrect() throws Exception {
        RestaurantDao restaurantDao = new RestaurantDaoImpl();
        Restaurant restaurant = new Restaurant()
                .id(1L)
                .address("")
                .name("Rest");
        restaurantDao.updateRestaurant(restaurant);
        assert restaurantDao.readRestaurant(1L).getAddress().equals("Rest");

    }
}
