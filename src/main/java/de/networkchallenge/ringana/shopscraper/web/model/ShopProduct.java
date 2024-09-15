package de.networkchallenge.ringana.shopscraper.web.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.util.Map;

@Data
@Jacksonized
@Builder
@RegisterReflectionForBinding
public class ShopProduct {
    private String matchcode;
    private String category;

    @JsonAnySetter
    @Singular("any")
    private Map<String, Object> any;

    @JsonAnyGetter
    public Map<String, Object> getAny() {
        return any;
    }
}
