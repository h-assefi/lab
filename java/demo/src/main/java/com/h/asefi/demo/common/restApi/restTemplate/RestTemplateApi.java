package com.h.asefi.demo.common.restApi.restTemplate;

import com.h.asefi.demo.common.restApi.RestApi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestTemplateApi implements RestApi {

    private final RestTemplate restTemplate;

    /**
     * Constructs a new RestTemplateApi with the provided RestTemplate instance.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     */
    public RestTemplateApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sends an HTTP GET request to the specified URL with optional headers.
     *
     * @param url     The target URL for the GET request.
     * @param headers Optional HTTP headers to include in the request.
     * @return ResponseEntity containing the response body and status code.
     */
    @Override
    public ResponseEntity<?> get(String url, Map<String, String> headers) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                getHttpEntity(headers),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    /**
     * Sends an HTTP PUT request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the PUT request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     */
    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    /**
     * Sends an HTTP POST request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the POST request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     */
    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    /**
     * Sends an HTTP DELETE request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the DELETE request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     */
    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    /**
     * Creates an HttpEntity with only headers.
     *
     * @param headers HTTP headers to include in the entity.
     * @return HttpEntity with headers.
     */
    private HttpEntity<String> getHttpEntity(Map<String, String> headers) {
        return new HttpEntity<>(getHttpHeaders(headers));
    }

    /**
     * Creates an HttpEntity with headers and a body.
     *
     * @param headers HTTP headers to include in the entity.
     * @param body    The request body to include (can be null).
     * @return HttpEntity with headers and body.
     */
    private HttpEntity<Object> getHttpEntity(Map<String, String> headers, Object body) {
        if (body != null)
            return new HttpEntity<>(body, getHttpHeaders(headers));
        else
            return new HttpEntity<>(getHttpHeaders(headers));
    }

    /**
     * Converts a Map of headers to HttpHeaders.
     *
     * @param headers Map of header names and values.
     * @return HttpHeaders object containing the provided headers.
     */
    private HttpHeaders getHttpHeaders(Map<String, String> headers) {
        HttpHeaders response = new HttpHeaders();
        if (headers != null)
            headers.forEach(response::set);

        return response;
    }
}
