package de.networkchallenge.ringana.shopscraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.networkchallenge.ringana.shopscraper.web.commands.FetchMatchcodes;
import de.networkchallenge.ringana.shopscraper.web.commands.FetchPrices;
import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.model.ShopPrice;
import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import de.networkchallenge.ringana.shopscraper.web.service.ShopPriceService;
import de.networkchallenge.ringana.shopscraper.web.service.ShopProductsService;
import de.networkchallenge.ringana.shopscraper.web.writer.PriceWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
@RegisterReflectionForBinding({ShopPrice.class})
public class FetchPricesRunner implements CommandLineRunner {
    private final ShopProductsService productsService;
    private final ShopPriceService priceService;
    private final RinganaConfig ringanaConfig;
    private final ObjectMapper om;

    @Override
    public void run(String... args) throws Exception {
        ShopPrice[] products = new FetchPrices(priceService, new ArrayList<>()).execute().getProducts();
        Arrays.stream(products).forEach(x -> log.info("got price for {}", x.getMatchcode()));
        new PriceWriter(Arrays.asList(products), ringanaConfig.getOutputFolder(), om).write();
    }
}
