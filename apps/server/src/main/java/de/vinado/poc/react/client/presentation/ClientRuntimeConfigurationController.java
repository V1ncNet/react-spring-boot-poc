package de.vinado.poc.react.client.presentation;

import de.vinado.poc.react.client.support.ClientProperties;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ClientRuntimeConfigurationController {

    private static final String APPLICATION_JAVASCRIPT = "application/javascript";

    private final ClientProperties clientProperties;

    @GetMapping(value = "/config.js", produces = APPLICATION_JAVASCRIPT)
    public ResponseEntity<String> runtimeEnv() {
        Map<String, String> properties = clientProperties.getProperties();

        StringBuilder builder = new StringBuilder("window.__ENV__={");
        properties.forEach(appendTo(builder));
        builder.append("};");

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(12, TimeUnit.HOURS).cachePublic())
            .body(builder.toString());
    }

    private static BiConsumer<String, String> appendTo(StringBuilder builder) {
        return (key, value) -> builder.append(key).append(":\"").append(value).append("\",");
    }
}
