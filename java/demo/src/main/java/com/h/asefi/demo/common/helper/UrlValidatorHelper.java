package com.h.asefi.demo.common.helper;


import com.h.asefi.demo.common.exception.exceptionTypes.CustomException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlValidatorHelper {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isUrlValid(String url) {
        try {
            new URI(url).toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException | NullPointerException | IllegalArgumentException e) {
            return false;
        }
    }

    public static void checkUrlValidThrowException(String url) throws CustomException {
        if (!isUrlValid(url))
            throw new CustomException("Invalid URL format");
    }
}
