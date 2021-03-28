package de.networkchallenge.ringana.shopscraper;

import de.networkchallenge.ringana.shopscraper.web.configuration.SpringTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = SpringTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ShopscraperApplicationTests {

	@Test
	void contextLoads() {
	}

}
