package de.networkchallenge.ringana.shopscraper.web.commands;

import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.configuration.SpringTestConfig;
import de.networkchallenge.ringana.shopscraper.web.helper.TestHelper;
import de.networkchallenge.ringana.shopscraper.web.service.ShopProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(classes = SpringTestConfig.class)
class FetchMatchcodesTest extends Assertions {
    @Autowired
    private ShopProductsService service;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RinganaConfig ringanaConfig;

    @Test
    void getMatchcodes() throws URISyntaxException {
        MockRestServiceServer mockServerReader = MockRestServiceServer.createServer(restTemplate);
        mockServerReader.expect(ExpectedCount.once(),
                requestTo(new URI(ringanaConfig.getProductsUrl())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(TestHelper.readResource("/shop/products.json"))
                );
        //Mockito.when()

        FetchMatchcodes matchcodes = new FetchMatchcodes(service).execute();
        assertEquals(117, matchcodes.getMatchcodes().size());
        mockServerReader.verify();
    }
}