package com.emard.currencyexchangeservice.controller;

import com.emard.currencyexchangeservice.model.ExchangeValue;
import com.emard.currencyexchangeservice.model.ExchangevalueRepository;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyController {
    private final Environment environment;
    private final ExchangevalueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue e = repository.findByFromCAndToC(from, to);
        e.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
        return e;
        //return new ExchangeValue(1000l, from, to, BigDecimal.valueOf(70), Integer.valueOf(environment.getProperty("local.server.port")));
    }
}
