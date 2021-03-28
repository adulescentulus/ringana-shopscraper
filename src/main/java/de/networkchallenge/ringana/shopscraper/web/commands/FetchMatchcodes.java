package de.networkchallenge.ringana.shopscraper.web.commands;

import de.networkchallenge.ringana.shopscraper.web.model.ShopProduct;
import de.networkchallenge.ringana.shopscraper.web.service.ShopProductsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Getter
public class FetchMatchcodes {
    private final ShopProductsService productsService;
    private Set<String> matchcodes;

    public FetchMatchcodes execute() {
        ShopProduct[] products = productsService.fetchAllProducts();
        log.info("fetched {} products", products.length);
        matchcodes = Arrays.stream(products).map(ShopProduct::getMatchcode).collect(Collectors.toSet());
        log.info("extracted {}", matchcodes);
        return this;
    }
}
