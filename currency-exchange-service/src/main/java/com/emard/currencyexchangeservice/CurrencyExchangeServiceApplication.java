package com.emard.currencyexchangeservice;

import java.math.BigDecimal;

import com.emard.currencyexchangeservice.model.ExchangeValue;
import com.emard.currencyexchangeservice.model.ExchangevalueRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@Log4j2
@EnableDiscoveryClient
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ExchangevalueRepository repository){
		return args -> {
			ExchangeValue exc = new ExchangeValue();
			exc.setConversionMultiple(BigDecimal.valueOf(70));
			exc.setFromC("USD");
			exc.setToC("INR");
			exc.setPort(0);
			repository.save(exc);

			
			ExchangeValue exc1 = new ExchangeValue();
			exc1.setConversionMultiple(BigDecimal.valueOf(112));
			exc1.setFromC("EUR");
			exc1.setToC("INR");
			exc1.setPort(0);
			repository.save(exc1);

			ExchangeValue exc2 = new ExchangeValue();

			exc2.setConversionMultiple(BigDecimal.valueOf(25));
			exc2.setFromC("AUD");
			exc2.setToC("INR");
			exc2.setPort(0);
			repository.save(exc2);

			repository.findAll().forEach(c -> {
				log.info("[{}]",c);
			});
		};
		
	}

}
