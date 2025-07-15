package com.h.asefi.demo.common.helper.jsonHelper;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonHelper {
    /**
     * Serializes the given object to its JSON string representation.
     *
     * @param object the object to serialize
     * @return the JSON string representation of the object
     * @throws JsonProcessingException if serialization fails
     */
    String parseToString(Object object) throws JsonProcessingException;

    /**
     * Deserializes the given JSON string into an object of the specified type.
     *
     * @param jsonString the JSON string to deserialize
     * @param classType the class of the object to return
     * @param <T> the type of the object
     * @return the deserialized object of type T
     * @throws JsonProcessingException if deserialization fails
     */
    <T> T parseJson(String jsonString, Class<T> classType) throws JsonProcessingException;
}
