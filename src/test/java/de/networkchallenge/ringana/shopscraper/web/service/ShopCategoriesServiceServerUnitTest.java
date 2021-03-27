package de.networkchallenge.ringana.shopscraper.web.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import de.networkchallenge.ringana.shopscraper.web.configuration.SpringConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import de.networkchallenge.ringana.shopscraper.web.model.ShopCategory;


//@RestClientTest({ShopCategoriesService.class})
@SpringBootTest(classes = {ShopCategoriesService.class, SpringConfig.class})
//@ContextConfiguration(classes = SpringConfig.class)
public class ShopCategoriesServiceServerUnitTest extends Assertions {
    // @Autowired
    // RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ShopCategoriesService service;

    //@MockBean
    @Autowired
    RestTemplate restTemplate;

    //@Autowired
    //private MockRestServiceServer mockServer;

    // @BeforeEach
    // public void init() {
    //     mockServer = MockRestServiceServer.createServer(restTemplateBuilder.build());
    // }

    private String readResource(String path) {
        try (Scanner scanner = new Scanner(getClass().getResourceAsStream(path), "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        }
    }
    
    @Test                                                                                          
    public void givenMockingIsDoneByMockRestServiceServer_whenGetIsCalled_thenReturnsMockedObject() throws URISyntaxException {
        Assert.notNull(service);
        Assert.notNull(restTemplate);
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
          requestTo(new URI("http://localhost:8080/")))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withStatus(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(readResource("/shop/categories.json"))
        );
        //Mockito.when()
                       
        ShopCategory[] categories = service.fetchAllCategories();
        mockServer.verify();
    }
}
