package com.restaurantapp.models;

public enum Role {
    SOMETHING("10");

    private String value;

    Role(String s) {
        value = s;
    }

    public static Role findByValue(String roleValue) {
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
