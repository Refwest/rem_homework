package com.homework.rem.web.models;

import com.homework.rem.data.entities.CountryEntity;

import java.util.List;

public record CountryResponse(String countryISO2, String countryName, List<BasicBankDetailsResponse> banks) {
    public CountryResponse(CountryEntity countryEntity, List<BasicBankDetailsResponse> banks) {
        this(countryEntity.getCountryIso2(), countryEntity.getCountryName(), banks);
    }
}
