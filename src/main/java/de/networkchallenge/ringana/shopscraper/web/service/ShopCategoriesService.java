package de.networkchallenge.ringana.shopscraper.web.service;

import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.networkchallenge.ringana.shopscraper.web.model.ShopCategory;

@Service
@RequiredArgsConstructor
public class ShopCategoriesService {
    private final RestTemplate restTemplate;
    private final RinganaConfig ringanaConfig;

    public ShopCategory[] fetchAllCategories() {
        return restTemplate.getForObject(ringanaConfig.getCategoriesUrl(), ShopCategory[].class);
    }
}
