package com.h.asefi.demo.common.restApi.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h.asefi.demo.common.restApi.RestApi;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static java.net.http.HttpClient.newHttpClient;

@Service
public class HttpClientApi implements RestApi {

    private final ObjectMapper objectMapper;

    public HttpClientApi(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<?> get(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<?> response;
        try (HttpClient client = newHttpClient()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET();

            if (headers != null)
                headers.forEach(requestBuilder::header);

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        }

        return new ResponseEntity<>(response.body(), HttpStatusCode.valueOf(response.statusCode()));
    }

    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<?> response;
        try (HttpClient client = newHttpClient()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url));

            if (body != null)
                requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)));
            else
                requestBuilder.PUT(HttpRequest.BodyPublishers.noBody());

            if (headers != null)
                headers.forEach(requestBuilder::header);

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        }

        return new ResponseEntity<>(response.body(), HttpStatusCode.valueOf(response.statusCode()));
    }

    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<?> response;
        try (HttpClient client = newHttpClient()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url));

            if (body != null)
                requestBuilder.POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)));
            else
                requestBuilder.POST(HttpRequest.BodyPublishers.noBody());

            if (headers != null)
                headers.forEach(requestBuilder::header);

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        }

        return new ResponseEntity<>(response.body(), HttpStatusCode.valueOf(response.statusCode()));
    }

    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<?> response;
        try (HttpClient client = newHttpClient()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url));

            if (body != null)
                requestBuilder.method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)));
            else
                requestBuilder.DELETE();

            if (headers != null)
                headers.forEach(requestBuilder::header);

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        }

        return new ResponseEntity<>(response.body(), HttpStatusCode.valueOf(response.statusCode()));
    }
}
