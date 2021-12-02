package com.emard.currencycalculationservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.emard.currencycalculationservice.facade.CurrencyExcangeProxy;
import com.emard.currencycalculationservice.model.CalculatedAmount;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExcangeProxy proxy;
    // private static String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmountFeign(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        CalculatedAmount cResp = proxy.retrieveExchangeValue(from, to);

        return new CalculatedAmount(cResp.getId(), cResp.getFromC(), cResp.getToC(), cResp.getConversionMultiple(), quantity, 
        quantity.multiply(cResp.getConversionMultiple()), cResp.getPort());
    }


    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmount(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CalculatedAmount> responseEntity = new RestTemplate()
        .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
        CalculatedAmount.class, uriVariables);
        CalculatedAmount cResp = responseEntity.getBody();

        return new CalculatedAmount(cResp.getId(), cResp.getFromC(), cResp.getToC(), cResp.getConversionMultiple(), quantity, 
        quantity.multiply(cResp.getConversionMultiple()), cResp.getPort());
    }
}
