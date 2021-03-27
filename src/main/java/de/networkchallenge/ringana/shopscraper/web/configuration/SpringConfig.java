package de.networkchallenge.ringana.shopscraper.web.configuration;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

//@EnableAutoConfiguration
//@ComponentScan("de.networkchallenge.ringana.shopscraper.web.configuration")
@Configuration
@NoArgsConstructor
public class SpringConfig {

    //@Bean
    //public RestTemplateBuilder restTemplateBuilder() {
    //    return new RestTemplateBuilder();
    // }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}