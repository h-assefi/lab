package com.h.asefi.demo.common.helper;


import com.h.asefi.demo.common.exception.exceptionTypes.CustomException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utility class for validating URL strings.
 * <p>
 * Provides methods to check if a URL is valid and to throw a custom exception if the URL is invalid.
 */
public class UrlValidatorHelper {
    /**
     * Checks if the provided string is a valid URL.
     *
     * @param url the URL string to validate
     * @return {@code true} if the URL is valid, {@code false} otherwise
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isUrlValid(String url) {
        try {
            new URI(url).toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException | NullPointerException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Validates the provided URL string and throws a {@link CustomException} if it is invalid.
     *
     * @param url the URL string to validate
     * @throws CustomException if the URL format is invalid
     */
    public static void checkUrlValidThrowException(String url) throws CustomException {
        if (!isUrlValid(url))
            throw new CustomException("Invalid URL format");
    }
}
