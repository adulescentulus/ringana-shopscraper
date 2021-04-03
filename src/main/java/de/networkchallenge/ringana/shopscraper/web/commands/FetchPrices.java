package de.networkchallenge.ringana.shopscraper.web.commands;

import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import de.networkchallenge.ringana.shopscraper.web.service.ShopPriceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FetchPrices {
    private final ShopPriceService priceService;
    private final List<String> matchcodes;
    @Getter
    private ZapiProducts products;

    public FetchPrices execute() {
        products = priceService.fetchAllProductPrices(matchcodes);
        return this;
    }
}
