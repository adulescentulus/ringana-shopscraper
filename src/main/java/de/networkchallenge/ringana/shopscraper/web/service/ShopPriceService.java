package de.networkchallenge.ringana.shopscraper.web.service;

import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopPriceService {
    private final RestTemplate restTemplate;
    private final RinganaConfig ringanaConfig;

    public ZapiProducts fetchAllProductPrices() {
        log.info("Getting all product prices");
        return restTemplate.getForObject(ringanaConfig.getProductsUrl(), ZapiProducts.class);
    }
}
