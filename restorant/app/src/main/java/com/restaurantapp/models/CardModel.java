package com.restaurantapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardModel {
    private int image;
    private String title;
    private String text;
    private String desc;
    private Offer offer;
}
