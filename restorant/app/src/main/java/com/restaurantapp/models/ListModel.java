package com.restaurantapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListModel {
    private String firstName;
    private String lastName;
    private String offer;
    private String time;
}
