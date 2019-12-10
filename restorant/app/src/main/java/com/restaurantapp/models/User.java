package com.restaurantapp.models;

import android.icu.util.ULocale;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    private String token;

    private String email;

    private String address;

    private List<Category> category;

    private Role role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Restaurant restaurant;
}
