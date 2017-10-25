package com.zirxin.jtradier.service;

import com.zirxin.jtradier.dto.Quote;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest({"tradier.token=", "tradier.url=https://sandbox.tradier.com/v1"})
public class TradierMarketDataServiceTest {

    @Autowired
    private MarketDataService service;

    @MockBean
    private RestTemplateProvider restTemplateProvider;

    @Mock
    private RestTemplate template;

    @Mock
    ResponseEntity responseEntity;

    private Logger log = Logger.getRootLogger();

    public TradierMarketDataServiceTest() {
    }

    @Before
    public void onLoad() {
        given(responseEntity.getBody()).willReturn("{\"quotes\":{\"quote\":[{\"symbol\":\"GOOG\",\"description\":\"Alphabet Inc. - Class C Capital Stock\",\"exch\":\"Q\",\"type\":\"stock\",\"last\":969.6116,\"change\":1.17,\"change_percentage\":0.12,\"volume\":812950,\"average_volume\":0,\"last_volume\":500,\"trade_date\":1508871587000,\"open\":970.0,\"high\":972.23,\"low\":961.0,\"close\":null,\"prevclose\":968.45,\"week_52_high\":0.0,\"week_52_low\":0.0,\"bid\":969.28,\"bidsize\":4,\"bidexch\":\"Z\",\"bid_date\":1508871588000,\"ask\":969.67,\"asksize\":1,\"askexch\":\"K\",\"ask_date\":1508871587000,\"root_symbols\":\"GOOG,GOOG1,GOLG1\"},{\"symbol\":\"GOOGL\",\"description\":\"Alphabet Inc\",\"exch\":\"Q\",\"type\":\"stock\",\"last\":986.9,\"change\":1.37,\"change_percentage\":0.14,\"volume\":946329,\"average_volume\":1621298,\"last_volume\":300,\"trade_date\":1508871565000,\"open\":986.5,\"high\":989.26,\"low\":977.08,\"close\":null,\"prevclose\":985.54,\"week_52_high\":1016.3091,\"week_52_low\":743.59,\"bid\":986.34,\"bidsize\":1,\"bidexch\":\"Q\",\"bid_date\":1508871591000,\"ask\":987.06,\"asksize\":1,\"askexch\":\"Y\",\"ask_date\":1508871579000,\"root_symbols\":\"GOOGL\"}]}}");
        given(restTemplateProvider.getRestTemplate()).willReturn(template);
        given(template.exchange((String) Matchers.notNull(), (HttpMethod)Matchers.eq(HttpMethod.GET), Matchers.any(), (Class)Matchers.eq(String.class), new Object[0])).willReturn(this.responseEntity);
    }

    @Test
    public void getQuotesForGoogle() {
        List<Quote> quotes = this.service.getQuotes(Arrays.asList("SOMEQUOTE"));
        log.debug(quotes.stream().map((quote) ->
             quote.toString()
        ).collect(Collectors.joining(",")));
    }
}
