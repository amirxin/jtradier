package com.zirxin.jtradier.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zirxin.jtradier.dto.Quote;
import com.zirxin.jtradier.dto.Quotes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("MarketDataService")
public class TradierMarketDataService implements MarketDataService {

    private RestTemplateProvider provider;

    private static final String MARKET_QUOTES_PATH = "/markets/quotes?";

    private static final Logger log = Logger.getRootLogger();

    private ObjectMapper mapper;

    @Autowired
    public TradierMarketDataService(RestTemplateProvider provider) {

        this.provider = provider;

        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public List<Quote> getQuotes(List<String> symbols) {

        log.debug("Processing get symbols for" + symbols.stream().collect(Collectors.joining(",")));

        String finalUrl = MARKET_QUOTES_PATH + getSymbolQueryParam(symbols);

        String body = provider.getRestTemplate()
                .exchange(finalUrl, HttpMethod.GET, new HttpEntity(provider.getHeaders()), String.class, new Object[0])
                .getBody();

        try {
            return mapper.treeToValue(mapper.readTree(body).findPath("quotes"), Quotes.class).getQuotes();
        } catch (IOException var5) {
            log.error(body);
            log.error(var5);
            return Collections.emptyList();
        }
    }

    public Quote getQuote(String symbol) {
        return null;
    }

    private String getSymbolQueryParam(List<String> symbols) {
        return "symbols=" + symbols.stream().collect(Collectors.joining(","));
    }
}
