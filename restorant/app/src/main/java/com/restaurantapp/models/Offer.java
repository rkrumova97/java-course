package com.restaurantapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private Long id;

    private String text;

    private Long price;

    private Category category;

    private Restaurant restaurant;
}
