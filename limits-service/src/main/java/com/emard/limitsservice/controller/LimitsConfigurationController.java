package com.emard.limitsservice.controller;

import com.emard.limitsservice.bean.LimitsConfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LimitsConfigurationController {
    
    private final LimitsConfiguration limits;

    @GetMapping("/limits")
    public LimitsConfiguration returnLimits() {
        return new LimitsConfiguration(limits.getMaximum(), limits.getMinimum());
    }
}
