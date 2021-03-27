package de.networkchallenge.ringana.shopscraper.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCategory {
    private String matchcode;
    private String category;
}
