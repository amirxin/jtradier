package com.zirxin.jtradier.config;

import com.zirxin.jtradier.service.RestTemplateProvider;
import com.zirxin.jtradier.service.ServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ServiceProperties.class})
public class AppConfig {

    public AppConfig() {
    }

    @Bean
    public RestTemplateProvider restTemplateProvider(ServiceProperties properties) {
        return new RestTemplateProvider(properties.getToken(), properties.getUrl());
    }
}
