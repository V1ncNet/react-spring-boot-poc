package de.vinado.poc.react.client.infrastructure;

import de.vinado.poc.react.client.support.ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
class ClientConfiguration {
}
