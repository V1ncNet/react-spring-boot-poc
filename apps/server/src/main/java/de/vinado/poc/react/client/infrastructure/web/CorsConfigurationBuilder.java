package de.vinado.poc.react.client.infrastructure.web;

import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

class CorsConfigurationBuilder {

    private List<String> allowedOrigins;
    private List<String> allowedOriginPatterns;
    private List<String> allowedHeaders;
    private List<String> allowedMethods;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;

    public CorsConfigurationBuilder allowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
        return this;
    }

    public CorsConfigurationBuilder allowedOriginPatterns(List<String> allowedOriginPatterns) {
        this.allowedOriginPatterns = allowedOriginPatterns;
        return this;
    }

    public CorsConfigurationBuilder allowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
        return this;
    }

    public CorsConfigurationBuilder allowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
        return this;
    }

    public CorsConfigurationBuilder exposedHeaders(List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
        return this;
    }

    public CorsConfigurationBuilder allowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
        return this;
    }

    public CorsConfigurationBuilder maxAge(Long maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public CorsConfiguration build() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedOriginPatterns(allowedOriginPatterns);
        configuration.setAllowedHeaders(allowedHeaders);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setExposedHeaders(exposedHeaders);
        configuration.setAllowCredentials(allowCredentials);
        configuration.setMaxAge(maxAge);
        return configuration;
    }
}
