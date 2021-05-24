package de.networkchallenge.ringana.shopscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeHints;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

@SpringBootApplication
@TypeHints(value = {
		@TypeHint(typeNames = {"de.networkchallenge.ringana.shopscraper.web.model.ShopProduct", "de.networkchallenge.ringana.shopscraper.web.model.ShopProduct$ShopProductBuilder"}),
		@TypeHint(typeNames = {"de.networkchallenge.ringana.shopscraper.web.model.ShopPrice", "de.networkchallenge.ringana.shopscraper.web.model.ShopPrice$ShopPriceBuilder"}),
		@TypeHint(typeNames = {"de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts"})
})
public class ShopscraperApplication {

	public static void main(String[] args) throws IOException {
		System.out.write("Îƞŧéřƞȧŧǐøƞȧŀǐẑȧŧǐøƞ".getBytes());
		System.out.println(java.util.TimeZone.getDefault().getID());
		System.out.println("LANG=" + System.getenv("LANG"));
		System.out.println("Locale.getDefault()=" + Locale.getDefault());
		System.out.println("Charset.defaultCharset()=" + Charset.defaultCharset());
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		System.out.println("sun.jnu.encoding=" + System.getProperty("sun.jnu.encoding"));
		SpringApplication.run(ShopscraperApplication.class, args).close();
	}

}
