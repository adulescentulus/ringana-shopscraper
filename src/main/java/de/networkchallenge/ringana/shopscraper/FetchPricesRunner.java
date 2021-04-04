package de.networkchallenge.ringana.shopscraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.networkchallenge.ringana.shopscraper.web.commands.FetchMatchcodes;
import de.networkchallenge.ringana.shopscraper.web.commands.FetchPrices;
import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import de.networkchallenge.ringana.shopscraper.web.service.ShopPriceService;
import de.networkchallenge.ringana.shopscraper.web.service.ShopProductsService;
import de.networkchallenge.ringana.shopscraper.web.writer.PriceWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

@Component
@Slf4j
@RequiredArgsConstructor
public class FetchPricesRunner implements CommandLineRunner {
    private final ShopProductsService productsService;
    private final ShopPriceService priceService;
    private final RinganaConfig ringanaConfig;
    private final ObjectMapper om;

    @Override
    public void run(String... args) throws Exception {
        Set<String> matchcodes = new FetchMatchcodes(productsService).execute().getMatchcodes();
        log.info(matchcodes.toString());

        Spliterator<String> split = matchcodes.stream().spliterator();
        int chunkSize = 10;

        while(true) {
            List<String> chunk = new ArrayList<>(chunkSize);
            for (int i = 0; i < chunkSize && split.tryAdvance(chunk::add); i++){}
            if (chunk.isEmpty()) break;
            log.info("getting prices for matchcodes: {}", chunk);
            ZapiProducts products = new FetchPrices(priceService, chunk).execute().getProducts();
            products.getData().forEach(x -> log.info("got price for {}", x));
            new PriceWriter(products.getData(), ringanaConfig.getOutputFolder(), om).write();
        }
    }
}
