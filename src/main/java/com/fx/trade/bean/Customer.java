package com.fx.trade.bean;

import java.util.Arrays;

//TODO remove this enum amd persist supported customer as db entity and implement REST CRUD operations to manage it
public enum Customer {
    YODA1,
    YODA2;

    public static boolean isSupported(String value) {
        return Arrays.stream(values())
                .anyMatch(customer -> customer.name().equals(value));
    }
}
