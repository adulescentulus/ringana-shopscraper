package de.networkchallenge.ringana.shopscraper.web.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.Map;

@Data
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic=true)
public class ShopPrice {
    private String matchcode;

    @JsonAnySetter
    @Singular("any")
    private Map<String, Object> any;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date timestamp;

    @JsonAnyGetter
    public Map<String, Object> getAny() {
        return any;
    }
}
