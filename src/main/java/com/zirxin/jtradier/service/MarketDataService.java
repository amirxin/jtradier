package com.zirxin.jtradier.service;

import com.zirxin.jtradier.dto.Quote;

import java.util.List;

public interface MarketDataService {

    List<Quote> getQuotes(List<String> var1);

    Quote getQuote(String var1);
}
