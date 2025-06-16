package com.h.asefi.demo.common.restApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.h.asefi.demo.common.restApi.converter.RestApiConverter;
import com.h.asefi.demo.common.restApi.httpClient.HttpClientApi;
import com.h.asefi.demo.common.restApi.restTemplate.RestTemplateApi;
import com.h.asefi.demo.common.restApi.type.RestApiToolService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestApiServiceImpl implements RestApiService, RestApiConverter {

    private final ObjectMapper objectMapper;
    private final HttpClientApi httpClientApi;
    private final RestTemplateApi restTemplateApi;

    @Setter
    @Getter
    private RestApiToolService restApiToolService;

    public RestApiServiceImpl() {
        this.objectMapper = new ObjectMapper();
        this.httpClientApi = new HttpClientApi(objectMapper);
        this.restTemplateApi = new RestTemplateApi(new RestTemplate());
        restApiToolService = RestApiToolService.HttpClient;

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T, Y> T convertValueFromInput(Y inputValue, Class<T> dtoClass) throws JsonProcessingException {
        T bodyData;

        if (inputValue instanceof LinkedHashMap<?, ?>) {
            bodyData = objectMapper.convertValue(inputValue, dtoClass);
        } else {
            bodyData = objectMapper.readValue(Objects.requireNonNull(inputValue).toString(), dtoClass);
        }

        return bodyData;
    }

    @Override
    public <T, Y> List<T> convertListValueFromInput(Y inputValue, Class<T> dtoClass) throws JsonProcessingException {
        List<T> bodyData;
        if (inputValue instanceof ArrayList) {
            bodyData = (List<T>) ((ArrayList) inputValue).stream()
                    .map(map -> objectMapper.convertValue(map, dtoClass))
                    .collect(Collectors.toList());
        } else {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, dtoClass);
            bodyData = objectMapper.readValue(inputValue.toString(), type);
        }

        return bodyData;
    }

    @Override
    public ResponseEntity<?> get(String url) throws URISyntaxException, IOException, InterruptedException {

        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.get(url, null);
            case RestTemplate -> restTemplateApi.get(url, null);
        };

    }

    @Override
    public ResponseEntity<?> get(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.get(url, headers);
            case RestTemplate -> restTemplateApi.get(url, headers);
        };
    }

    @Override
    public ResponseEntity<?> put(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, null, null);
            case RestTemplate -> restTemplateApi.put(url, null, null);
        };
    }

    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, headers, null);
            case RestTemplate -> restTemplateApi.put(url, headers, null);
        };
    }

    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, headers, body);
            case RestTemplate -> restTemplateApi.put(url, headers, body);
        };
    }

    @Override
    public ResponseEntity<?> post(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, null, null);
            case RestTemplate -> restTemplateApi.post(url, null, null);
        };
    }

    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, headers, null);
            case RestTemplate -> restTemplateApi.post(url, headers, null);
        };
    }

    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, headers, body);
            case RestTemplate -> restTemplateApi.post(url, headers, body);
        };
    }

    @Override
    public ResponseEntity<?> delete(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, null, null);
            case RestTemplate -> restTemplateApi.delete(url, null, null);
        };
    }

    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, headers, null);
            case RestTemplate -> restTemplateApi.delete(url, headers, null);
        };
    }

    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, headers, body);
            case RestTemplate -> restTemplateApi.delete(url, headers, body);
        };
    }


}
