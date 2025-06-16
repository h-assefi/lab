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

    public RestTemplateApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> get(String url, Map<String, String> headers) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                getHttpEntity(headers),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body) {
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                getHttpEntity(headers, body),
                Object.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    private HttpEntity<String> getHttpEntity(Map<String, String> headers) {
        return new HttpEntity<>(getHttpHeaders(headers));
    }

    private HttpEntity<Object> getHttpEntity(Map<String, String> headers, Object body) {
        if (body != null)
            return new HttpEntity<>(body, getHttpHeaders(headers));
        else
            return new HttpEntity<>(getHttpHeaders(headers));
    }

    private HttpHeaders getHttpHeaders(Map<String, String> headers) {
        HttpHeaders response = new HttpHeaders();
        if (headers != null)
            headers.forEach(response::set);

        return response;
    }
}
