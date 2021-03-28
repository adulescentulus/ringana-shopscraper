package de.networkchallenge.ringana.shopscraper.web.service;

import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.model.ShopProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopProductsService {
    private final RestTemplate restTemplate;
    private final RinganaConfig ringanaConfig;

    public ShopProduct[] fetchAllProducts() {
        log.info("Getting all products");
        return restTemplate.getForObject(ringanaConfig.getProductsUrl(), ShopProduct[].class);
    }
}
