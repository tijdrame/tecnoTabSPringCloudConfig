package com.emard.currencycalculationservice.conf;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
            ConfigurableApplicationContext context) {
        log.info("Configuring Load balancer to prefer same instance");
        return ServiceInstanceListSupplier.builder()
        //.withDiscoveryClient()
                .withBlockingDiscoveryClient()
                .withSameInstancePreference()
                .build(context);
    }

}
