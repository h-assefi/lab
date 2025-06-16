package com.h.asefi.demo.common.restApi;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface RestApiService {

    /**
     * Sends an HTTP GET request to the specified URL.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> get(String url) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP GET request to the specified URL with the given headers.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> get(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP PUT request to the specified URL.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> put(String url) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP PUT request to the specified URL with the given headers.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> put(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP PUT request to the specified URL with the given headers and body.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> put(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP POST request to the specified URL.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> post(String url) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP POST request to the specified URL with the given headers.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> post(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP POST request to the specified URL with the given headers and body.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> post(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP DELETE request to the specified URL.
     *
     * @param url the target URL
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> delete(String url) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP DELETE request to the specified URL with the given headers.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> delete(String url, Map<String, String> headers) throws URISyntaxException, IOException, InterruptedException;

    /**
     * Sends an HTTP DELETE request to the specified URL with the given headers and body.
     *
     * @param url     the target URL
     * @param headers the HTTP headers to include in the request
     * @param body    the request body to send (may be null)
     * @return the response entity from the server
     * @throws URISyntaxException   if the URL is not formatted correctly
     * @throws IOException         if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    ResponseEntity<?> delete(String url, Map<String, String> headers, Object body) throws URISyntaxException, IOException, InterruptedException;
}
