package com.h.asefi.demo.common.restApi.type;

/**
 * Enum representing the available HTTP client implementations for REST API
 * operations.
 * <p>
 * Use this enum to select which underlying HTTP client should be used by the
 * {@code RestApiServiceImpl}:
 * <ul>
 * <li>{@link #HttpClient} - Uses Java's built-in HttpClient
 * (java.net.http.HttpClient).</li>
 * <li>{@link #RestTemplate} - Uses Spring's RestTemplate.</li>
 * </ul>
 * This allows switching between different HTTP client strategies at runtime.
 */
public enum RestApiToolService {
    HttpClient,
    RestTemplate
}
