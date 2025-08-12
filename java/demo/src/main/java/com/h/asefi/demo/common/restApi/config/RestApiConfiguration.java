package com.h.asefi.demo.common.restApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiConfiguration {
    /**
     * Provides a singleton RestTemplate instance for use by the application.
     *
     * @return a RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
