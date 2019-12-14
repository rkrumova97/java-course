package com.restaurantapp.models;

public enum Role {
    ;

    private String value;

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
