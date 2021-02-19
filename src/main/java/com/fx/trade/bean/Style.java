package com.fx.trade.bean;

import java.util.Arrays;

public enum Style {

    AMERICAN,
    EUROPEAN;

    public static boolean isSupported(String value) {
        return Arrays.stream(values())
                .anyMatch(style -> style.name().equals(value));
    }

}
