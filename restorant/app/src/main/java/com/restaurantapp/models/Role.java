package com.restaurantapp.models;

public enum Role {
    CLIENT("10"),
    EMPLOYEE("20");

    private String value;

    Role(String s) {
        value = s;
    }

    public static Role findByValue(String roleValue) {
        switch (roleValue) {
            case "10":
                return CLIENT;
            case "20":
                return EMPLOYEE;
            default:
                return null;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
