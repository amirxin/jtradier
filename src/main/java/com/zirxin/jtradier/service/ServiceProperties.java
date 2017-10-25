package com.zirxin.jtradier.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tradier")
public class ServiceProperties {

    private String token;

    private String url;

    public ServiceProperties() {
    }

    public String getToken() {
        return this.token;
    }

    public String getUrl() {
        return this.url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
