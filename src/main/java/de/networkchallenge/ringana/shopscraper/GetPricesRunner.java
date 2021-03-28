package de.networkchallenge.ringana.shopscraper;

import de.networkchallenge.ringana.shopscraper.web.commands.FetchMatchcodes;
import de.networkchallenge.ringana.shopscraper.web.service.ShopProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetPricesRunner implements CommandLineRunner {
    private final ShopProductsService productsService;

    @Override
    public void run(String... args) throws Exception {
        log.info(new FetchMatchcodes(productsService).execute().getMatchcodes().toString());
    }
}
