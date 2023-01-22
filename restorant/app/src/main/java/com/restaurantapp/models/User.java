package com.restaurantapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
@TypeConverters(UserConverter.class)
public class User {
    @PrimaryKey
    private Long id;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String token;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String address;

    @ColumnInfo
    private List<Category> categories;

    @ColumnInfo(name = "role_id")
    private Role role;

    @ColumnInfo
    private String firstName;

    @ColumnInfo
    private String lastName;

    @ColumnInfo
    private String phoneNumber;

    @ColumnInfo(name = "restaurant_id")
    private Restaurant restaurant;

    public User() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User id(Long id){
        setId(id);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User username(String username){
        setUsername(username);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password){
        setPassword(password);
        return this;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User token(String token){
        setToken(token);
        return this;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email){
        setEmail(email);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User address(String address){
        setAddress(address);
        return this;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User categories(List<Category> categories){
        setCategories(categories);
        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User role(Role role){
        setRole(role);
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User firstName(String firstName){
        setFirstName(firstName);
        return this;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User lastName(String lastName){
        setLastName(lastName);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User phoneNumber(String phoneNumber){
        setPhoneNumber(phoneNumber);
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User restaurant(Restaurant restaurant) {
        setRestaurant(restaurant);
        return this;
    }
}
