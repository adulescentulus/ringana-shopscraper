package de.networkchallenge.ringana.shopscraper.web.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.networkchallenge.ringana.shopscraper.web.model.ShopPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PriceWriter {
    private final List<ShopPrice> prices;
    private final String outputFolder;
    private final ObjectMapper objectMapper;

    public void write() {
        for (ShopPrice shopPrice : prices) {
            new File(outputFolder).mkdirs();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(outputFolder + File.separator + shopPrice.getMatchcode() + ".json");
                fileWriter.write(objectMapper.writeValueAsString(shopPrice));
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                assert fileWriter != null;
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
