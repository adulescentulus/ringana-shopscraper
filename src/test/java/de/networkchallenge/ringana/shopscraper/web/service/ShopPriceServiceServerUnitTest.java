package de.networkchallenge.ringana.shopscraper.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.networkchallenge.ringana.shopscraper.web.configuration.RinganaConfig;
import de.networkchallenge.ringana.shopscraper.web.configuration.SpringTestConfig;
import de.networkchallenge.ringana.shopscraper.web.helper.TestHelper;
import de.networkchallenge.ringana.shopscraper.web.model.ZapiProducts;
import org.hamcrest.core.StringStartsWith;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


//@RestClientTest({ShopCategoriesService.class})
//@ContextConfiguration(classes = SpringConfig.class)
@SpringBootTest(classes = {
        SpringTestConfig.class
})
public class ShopPriceServiceServerUnitTest extends Assertions {
    @Autowired
    private ShopPriceService service;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RinganaConfig ringanaConfig;

    @Autowired
    private ObjectMapper om;
    
    @Test                                                                                          
    public void whenQueryShopProducts_thenItemsAreReturned() throws URISyntaxException, JsonProcessingException, JSONException {
        MockRestServiceServer mockServerReader = MockRestServiceServer.createServer(restTemplate);
        mockServerReader.expect(ExpectedCount.once(),
            requestTo(StringStartsWith.startsWith(ringanaConfig.getPricesUrl())))
              .andExpect(method(HttpMethod.GET))
              .andExpect(queryParam("matchcodes", "test1", "test2"))
              .andRespond(withStatus(HttpStatus.OK)
              .contentType(MediaType.APPLICATION_JSON)
              .body(TestHelper.readResource("/shop/prices.json"))
        );
        //Mockito.when()
                       
        ZapiProducts prices = service.fetchAllProductPrices(Arrays.asList("test1", "test2"));
        mockServerReader.verify();

        assertNotNull(om);
        String pricesJson = om.writeValueAsString(prices);
        assertNotNull(pricesJson);
        JSONAssert.assertEquals(TestHelper.readResource("/shop/prices.json"), pricesJson, JSONCompareMode.LENIENT);
    }
}
