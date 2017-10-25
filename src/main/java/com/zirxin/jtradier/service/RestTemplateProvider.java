package com.zirxin.jtradier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateProvider {

    private final String token;

    private final String baseUrl;

    private RestTemplateBuilder builder;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    public RestTemplateProvider(String token, String url) {

        if (!token.startsWith(TOKEN_PREFIX)) {
            token = TOKEN_PREFIX + token;
        }

        this.baseUrl = url;
        this.token = token;
    }

    @Autowired
    public void setRestTemplateBuilder(RestTemplateBuilder builder) {
        this.builder = builder.rootUri(this.baseUrl);
    }

    public RestTemplate getRestTemplate() {
        return this.builder.build();
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.token);
        headers.add("Accept", "application/json");
        return headers;
    }
}
