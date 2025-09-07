package com.h.asefi.demo.common.helper.base64Helper;

import java.util.Base64;

public class Base64Helper {

    public static boolean isBase64(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        if (!input.matches("^[A-Za-z0-9+/]*={0,2}$") || input.length() % 4 != 0) {
            return false; // Invalid characters or incorrect padding
        }

        try {
            Base64.getDecoder().decode(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Not valid Base64
        }
    }
}
