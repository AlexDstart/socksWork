package com.example.socks.util;

public class Constants {
    public static final String SOCKS_NOT_FOUND = "Socks not found";
    public static final String SOCKS_EXIST = "This type of socks already exists";
    public static final String SOCKS_NOT_ENOUGH = "Not enough socks in the warehouse";
    public static final String OPERATION_NOT_FOUND = "Operation not found";
    public static final String MORE_THAN_OPERATION = "moreThan";
    public static final String LESS_THAN_OPERATION = "lessThan";
    public static final String EQUAL_OPERATION = "equal";
    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
