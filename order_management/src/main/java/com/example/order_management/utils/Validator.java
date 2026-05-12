package com.example.order_management.utils;



public class Validator {

    public static boolean isValidName(String value) {
        return value != null && value.length() >= 2;
    }
}