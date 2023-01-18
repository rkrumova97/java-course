package com.restaurantapp.models;

public class ListModel {
    private String firstName;
    private String lastName;
    private String offer;
    private String time;

    public ListModel() {
    }

    public ListModel(String firstName, String lastName, String offer, String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offer = offer;
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
