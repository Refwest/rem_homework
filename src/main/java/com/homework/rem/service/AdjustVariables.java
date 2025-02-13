package com.homework.rem.service;

import org.apache.commons.lang3.StringUtils;

public class AdjustVariables {

    public static String unicodeInput(String input) {
        if (input == null) {
            return null;
        } else {
            return StringUtils.stripAccents(input).toUpperCase();
        }
    }
}
