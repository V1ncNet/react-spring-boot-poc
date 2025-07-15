package de.vinado.poc.react.client.infrastructure.web;

import de.vinado.poc.react.client.support.ClientProperties;
import de.vinado.poc.react.client.support.ClientProperties.Cors;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
class ClientWebConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource(ClientProperties properties) {
        CorsConfiguration configuration = new CorsConfiguration();
        for (CorsConfiguration clientCorsConfiguration : getClientCorsPolicies(properties)) {
            configuration = configuration.combine(clientCorsConfiguration);
        }

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private List<CorsConfiguration> getClientCorsPolicies(ClientProperties properties) {
        return properties.getCors()
            .entrySet().stream()
            .map(pairwise(this::getClientCorsPolicy))
            .collect(Collectors.toList());
    }

    private static <K, V, R> Function<Entry<K, V>, R> pairwise(BiFunction<K, V, R> mapper) {
        return entry -> mapper.apply(entry.getKey(), entry.getValue());
    }

    private CorsConfiguration getClientCorsPolicy(String registrationId, Cors properties) {
        CorsConfigurationBuilder builder = new CorsConfigurationBuilder();

        apply(builder, properties);
        return builder.build();
    }

    private void apply(CorsConfigurationBuilder target, Cors source) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(source::getAllowedOrigins).to(target::allowedOrigins);
        map.from(source::getAllowedOriginPatterns).to(target::allowedOriginPatterns);
        map.from(source::getAllowedHeaders).to(target::allowedHeaders);
        map.from(source::getAllowedMethods).as(ClientWebConfiguration::serialize).to(target::allowedMethods);
        map.from(source::getExposedHeaders).to(target::exposedHeaders);
        map.from(source::getAllowCredentials).to(target::allowCredentials);
        map.from(source::getMaxAge).to(target::maxAge);
    }

    private static List<String> serialize(List<HttpMethod> httpMethods) {
        return httpMethods.stream().map(HttpMethod::name).collect(Collectors.toList());
    }
}
