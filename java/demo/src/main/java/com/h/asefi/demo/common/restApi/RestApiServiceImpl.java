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

/**
 * Implementation of {@link RestApiService} and {@link RestApiConverter}.
 * <p>
 * This service provides a unified interface for making HTTP requests (GET,
 * POST, PUT, DELETE)
 * using either Java's HttpClient or Spring's RestTemplate, based on the
 * selected {@link RestApiToolService}.
 * It also provides methods for converting input values to DTOs using Jackson's
 * ObjectMapper.
 * </p>
 * <ul>
 * <li>Supports switching between HttpClient and RestTemplate at runtime.</li>
 * <li>Handles serialization and deserialization of request and response
 * bodies.</li>
 * <li>Implements utility methods for converting single objects and lists from
 * various input types.</li>
 * </ul>
 *
 * @author
 */
@Service
public class RestApiServiceImpl implements RestApiService, RestApiConverter {

    private final ObjectMapper objectMapper;
    private final HttpClientApi httpClientApi;
    private final RestTemplateApi restTemplateApi;

    @Setter
    @Getter
    private RestApiToolService restApiToolService;

    /**
     * Constructs a new RestApiServiceImpl with default configuration.
     * Initializes ObjectMapper, HttpClientApi, and RestTemplateApi.
     * Sets the default tool to HttpClient.
     */
    public RestApiServiceImpl() {
        this.objectMapper = new ObjectMapper();
        this.httpClientApi = new HttpClientApi(objectMapper);
        this.restTemplateApi = new RestTemplateApi(new RestTemplate());
        restApiToolService = RestApiToolService.HttpClient;

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

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

    /**
     * Converts the given input value to a list of instances of the specified DTO
     * class.
     *
     * @param inputValue the input value to convert (e.g., JSON array, List, etc.)
     * @param dtoClass   the target class to convert to
     * @param <T>        the type of the target DTO
     * @param <Y>        the type of the input value
     * @return a list of instances of the target DTO class
     * @throws JsonProcessingException if conversion fails
     */
    @Override
    public <T, Y> List<T> convertListValueFromInput(Y inputValue, Class<T> dtoClass) throws JsonProcessingException {
        List<T> bodyData;
        if (inputValue instanceof ArrayList<?> inputList) {
            bodyData = inputList.stream()
                    .map(element -> objectMapper.convertValue(element, dtoClass))
                    .collect(Collectors.toList());
        } else {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, dtoClass);
            bodyData = objectMapper.readValue(inputValue.toString(), type);
        }

        return bodyData;
    }

    /**
     * Sends an HTTP GET request to the specified URL using the selected HTTP
     * client.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> get(String url) throws URISyntaxException, IOException, InterruptedException {

        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.get(url, null);
            case RestTemplate -> restTemplateApi.get(url, null);
        };

    }

    /**
     * Sends an HTTP GET request to the specified URL with the given headers using
     * the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> get(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.get(url, headers);
            case RestTemplate -> restTemplateApi.get(url, headers);
        };
    }

    /**
     * Sends an HTTP PUT request to the specified URL using the selected HTTP
     * client.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> put(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, null, null);
            case RestTemplate -> restTemplateApi.put(url, null, null);
        };
    }

    /**
     * Sends an HTTP PUT request to the specified URL with the given headers using
     * the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, headers, null);
            case RestTemplate -> restTemplateApi.put(url, headers, null);
        };
    }

    /**
     * Sends an HTTP PUT request to the specified URL with the given headers and
     * body using the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.put(url, headers, body);
            case RestTemplate -> restTemplateApi.put(url, headers, body);
        };
    }

    /**
     * Sends an HTTP POST request to the specified URL using the selected HTTP
     * client.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> post(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, null, null);
            case RestTemplate -> restTemplateApi.post(url, null, null);
        };
    }

    /**
     * Sends an HTTP POST request to the specified URL with the given headers using
     * the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, headers, null);
            case RestTemplate -> restTemplateApi.post(url, headers, null);
        };
    }

    /**
     * Sends an HTTP POST request to the specified URL with the given headers and
     * body using the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.post(url, headers, body);
            case RestTemplate -> restTemplateApi.post(url, headers, body);
        };
    }

    /**
     * Sends an HTTP DELETE request to the specified URL using the selected HTTP
     * client.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> delete(String url) throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, null, null);
            case RestTemplate -> restTemplateApi.delete(url, null, null);
        };
    }

    /**
     * Sends an HTTP DELETE request to the specified URL with the given headers
     * using the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, headers, null);
            case RestTemplate -> restTemplateApi.delete(url, headers, null);
        };
    }

    /**
     * Sends an HTTP DELETE request to the specified URL with the given headers and
     * body using the selected HTTP client.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send (may be null)
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
        return switch (restApiToolService) {
            case HttpClient -> httpClientApi.delete(url, headers, body);
            case RestTemplate -> restTemplateApi.delete(url, headers, body);
        };
    }

}
