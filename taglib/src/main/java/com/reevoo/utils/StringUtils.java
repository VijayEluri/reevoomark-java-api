package com.reevoo.utils;

/**
 * Utility String related methods.
 */
public class StringUtils {

    /**
     * Returns whether the string value is null or empty.
     * @param stringValue
     * @return
     */
    public static boolean isEmpty(String stringValue) {
        return stringValue == null || stringValue.trim().equals("");
    }
}
