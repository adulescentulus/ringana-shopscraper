package de.networkchallenge.ringana.shopscraper.web.configuration;

import de.networkchallenge.ringana.shopscraper.ShopscraperApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "de.networkchallenge", excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ShopscraperApplication.class)})
@EnableAutoConfiguration
public class SpringTestConfig {
}
