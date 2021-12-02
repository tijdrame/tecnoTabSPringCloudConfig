package com.emard.currencyexchangeservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangevalueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromCAndToC(String from, String to);
}
