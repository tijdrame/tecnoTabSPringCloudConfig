package com.emard.currencycalculationservice.facade;

import com.emard.currencycalculationservice.conf.LoadBalancerConfiguration;
import com.emard.currencycalculationservice.model.CalculatedAmount;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url = "http://localhost:8000/")
@FeignClient(name="currency-exchange-service")
@LoadBalancerClient(name = "currency-exchange-service", configuration=LoadBalancerConfiguration.class)
public interface CurrencyExcangeProxy {
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CalculatedAmount retrieveExchangeValue(@PathVariable ("from") String from, @PathVariable("to") String to) ;
}