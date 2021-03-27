package de.networkchallenge.ringana.shopscraper.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ringana")
public class RinganaConfig {
    private String categoriesUrl;
}
