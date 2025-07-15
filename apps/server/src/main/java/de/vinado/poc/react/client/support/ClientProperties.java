package de.vinado.poc.react.client.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("client")
@Getter
@Setter
public class ClientProperties {

    private Map<String, String> properties = new HashMap<>();

    private final Map<String, Cors> cors = new HashMap<>();


    @Getter
    @Setter
    public static class Cors {

        private List<String> allowedOrigins;
        private List<String> allowedOriginPatterns;
        private List<String> allowedHeaders;
        private List<HttpMethod> allowedMethods;
        private List<String> exposedHeaders;
        private Boolean allowCredentials;
        private Long maxAge;

        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = CollectionUtils.isEmpty(allowedOrigins) ? null : allowedOrigins;
        }

        public void setAllowedOriginPatterns(List<String> allowedOriginPatterns) {
            this.allowedOriginPatterns = CollectionUtils.isEmpty(allowedOriginPatterns) ? null : allowedOriginPatterns;
        }

        public void setAllowedHeaders(List<String> allowedHeaders) {
            this.allowedHeaders = CollectionUtils.isEmpty(allowedHeaders) ? null : allowedHeaders;
        }

        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = CollectionUtils.isEmpty(allowedMethods)
                ? null
                : allowedMethods.stream().map(HttpMethod::valueOf).collect(Collectors.toList());
        }

        public void setExposedHeaders(List<String> exposedHeaders) {
            this.exposedHeaders = CollectionUtils.isEmpty(exposedHeaders) ? null : exposedHeaders;
        }

        public void setAllowCredentials(String allowCredentials) {
            this.allowCredentials = StringUtils.hasText(allowCredentials)
                ? Boolean.parseBoolean(allowCredentials)
                : null;
        }

        public void setMaxAge(String maxAge) {
            this.maxAge = StringUtils.hasText(maxAge)
                ? Long.parseLong(maxAge)
                : null;
        }
    }
}
