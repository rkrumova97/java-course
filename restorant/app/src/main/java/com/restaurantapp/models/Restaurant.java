package com.restaurantapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long id;

    private String name;

    private String address;

    private List<Category> categories;
}
