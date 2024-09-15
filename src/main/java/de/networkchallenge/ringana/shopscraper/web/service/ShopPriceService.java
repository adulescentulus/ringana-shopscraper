package de.networkchallenge.ringana.shopscraper.web.service;

import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.model.ShopPrice;
import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopPriceService {
    private final RestTemplate restTemplate;
    private final RinganaConfig ringanaConfig;

    public ShopPrice[] fetchAllProductPrices(List<String> matchcodes) {
        log.info("Getting all product prices");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ringanaConfig.getPricesUrl())
                .queryParam("matchcodes[]", matchcodes);
        return restTemplate.getForEntity(builder.build(false).toUriString(), ShopPrice[].class).getBody();
    }
}
