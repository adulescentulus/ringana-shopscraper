package de.networkchallenge.ringana.shopscraper.web.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@EnableAutoConfiguration
//@ComponentScan("de.networkchallenge.ringana.shopscraper.web.configuration")
@Configuration
public class SpringConfig {

    //@Bean
    //public RestTemplateBuilder restTemplateBuilder() {
    //    return new RestTemplateBuilder();
    // }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}