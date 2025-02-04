package com.homework.rem.web.models;

public record CountryRequest(String countryISO2, String countryName) {
    public CountryRequest(BankRequest bankRequest) {
        this(bankRequest.countryISO2(), bankRequest.countryName());
    }
}
