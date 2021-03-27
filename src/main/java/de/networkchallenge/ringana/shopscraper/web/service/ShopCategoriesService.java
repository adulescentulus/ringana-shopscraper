package de.networkchallenge.ringana.shopscraper.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.networkchallenge.ringana.shopscraper.web.handler.RestTemplateResponseErrorHandler;
import de.networkchallenge.ringana.shopscraper.web.model.ShopCategory;

@Service
public class ShopCategoriesService {
    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    public ShopCategoriesService() {

    }

    public ShopCategory[] fetchAllCategories() {
        return restTemplate.getForObject("/bars/4242", ShopCategory[].class);
    }
}
