package com.restaurantapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private Long id;

    private String text;

    private Long price;

    private Category category;

    private Restaurant restaurant;
}
