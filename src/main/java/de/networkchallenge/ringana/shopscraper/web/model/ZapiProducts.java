package de.networkchallenge.ringana.shopscraper.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterReflectionForBinding
public class ZapiProducts {
    List<ShopPrice> data;
    boolean success;
}
