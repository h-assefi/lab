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

    /**
     * Sends an HTTP GET request to the specified URL with optional headers.
     *
     * @param url     The target URL for the GET request.
     * @param headers Optional HTTP headers to include in the request.
     * @return ResponseEntity containing the response body and status code.
     * @throws URISyntaxException   If the URL is invalid.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public ResponseEntity<?> get(String url, Map<String, String> headers)
            throws URISyntaxException, IOException, InterruptedException {
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

    /**
     * Sends an HTTP PUT request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the PUT request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     * @throws URISyntaxException   If the URL is invalid.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    @Override
    public ResponseEntity<?> put(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
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

    /**
     * Sends an HTTP POST request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the POST request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     * @throws URISyntaxException   If the URL is invalid.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    @Override
    public ResponseEntity<?> post(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
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

    /**
     * Sends an HTTP DELETE request to the specified URL with optional headers and
     * body.
     *
     * @param url     The target URL for the DELETE request.
     * @param headers Optional HTTP headers to include in the request.
     * @param body    The request body to send (can be null).
     * @return ResponseEntity containing the response body and status code.
     * @throws URISyntaxException   If the URL is invalid.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    @Override
    public ResponseEntity<?> delete(String url, Map<String, String> headers, Object body)
            throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<?> response;
        try (HttpClient client = newHttpClient()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url));

            if (body != null)
                requestBuilder.method("DELETE",
                        HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)));
            else
                requestBuilder.DELETE();

            if (headers != null)
                headers.forEach(requestBuilder::header);

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        }

        return new ResponseEntity<>(response.body(), HttpStatusCode.valueOf(response.statusCode()));
    }
}
