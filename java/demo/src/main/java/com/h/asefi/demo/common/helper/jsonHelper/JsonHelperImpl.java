package com.h.asefi.demo.common.helper.jsonHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonHelperImpl implements JsonHelper {
    private final ObjectMapper objectMapper;

    public JsonHelperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String parseToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T parseJson(String jsonString, Class<T> classType) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, classType);
    }
}
