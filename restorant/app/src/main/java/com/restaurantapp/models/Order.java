package com.restaurantapp.models;

import java.time.LocalDateTime;

public class Order {
    private Long id;

    private User user;

    private Offer offer;

    private LocalDateTime localDateTime;
}
