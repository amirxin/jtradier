package com.zirxin.jtradier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quotes {
    @JsonProperty("quote")
    private List<Quote> quotes;

    public Quotes() {
    }

    public List<Quote> getQuotes() {
        return this.quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Quotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quotes quotes1 = (Quotes) o;

        return quotes != null ? quotes.equals(quotes1.quotes) : quotes1.quotes == null;
    }

    @Override
    public int hashCode() {
        return quotes != null ? quotes.hashCode() : 0;
    }

    public String toString() {
        return "Quotes(quotes=" + this.getQuotes() + ")";
    }
}
