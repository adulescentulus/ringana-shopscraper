package de.networkchallenge.ringana.shopscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"de.networkchallenge.ringana.shopscraper"})
public class ShopscraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopscraperApplication.class, args);
	}

}
