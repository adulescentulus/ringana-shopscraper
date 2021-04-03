package de.networkchallenge.ringana.shopscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeHints;

@SpringBootApplication
@TypeHints(value = {
		@TypeHint(typeNames = {"de.networkchallenge.ringana.shopscraper.web.model.ShopProduct", "de.networkchallenge.ringana.shopscraper.web.model.ShopProduct$ShopProductBuilder"}),
		@TypeHint(typeNames = {"de.networkchallenge.ringana.shopscraper.web.model.ShopPrice", "de.networkchallenge.ringana.shopscraper.web.model.ShopPrice$ShopPriceBuilder"})
})
public class ShopscraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopscraperApplication.class, args).close();
	}

}
