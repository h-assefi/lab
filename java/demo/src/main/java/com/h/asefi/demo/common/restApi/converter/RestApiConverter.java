package com.h.asefi.demo.common.restApi.converter;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Interface for converting input values to target DTO classes or lists of DTOs.
 * Typically used for serializing and deserializing HTTP request and response bodies.
 */
public interface RestApiConverter {
    /**
     * Converts the given input value to an instance of the specified DTO class.
     *
     * @param inputValue the input value to convert (e.g., JSON string, Map, etc.)
     * @param dtoClass   the target class to convert to
     * @param <T>        the type of the target DTO
     * @param <Y>        the type of the input value
     * @return an instance of the target DTO class
     * @throws JsonProcessingException if conversion fails
     */
    <T, Y> T convertValueFromInput(Y inputValue, Class<T> dtoClass) throws JsonProcessingException;

    /**
     * Converts the given input value to a list of instances of the specified DTO class.
     *
     * @param inputValue the input value to convert (e.g., JSON array, List, etc.)
     * @param dtoClass   the target class to convert to
     * @param <T>        the type of the target DTO
     * @param <Y>        the type of the input value
     * @return a list of instances of the target DTO class
     * @throws JsonProcessingException if conversion fails
     */
    <T, Y> List<T> convertListValueFromInput(Y inputValue, Class<T> dtoClass) throws JsonProcessingException;

}
