package com.h.asefi.demo.common.helper.jsonHelper;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonHelper {
    String parseToString(Object object) throws JsonProcessingException;

    <T> T parseJson(String jsonString, Class<T> classType) throws JsonProcessingException;
}
