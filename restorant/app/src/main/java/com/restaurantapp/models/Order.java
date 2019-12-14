package com.restaurantapp.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;

    private User user;

    private Offer offer;

    private LocalDateTime localDateTime;
}
