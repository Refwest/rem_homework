package com.homework.rem.web.models;

import com.homework.rem.data.entities.CountryEntity;

import java.util.List;

public record CountryResponse(String countryISO2, String countryName, List<BasicBankDetailsResponse> swiftCodes) {
    public CountryResponse(CountryEntity countryEntity, List<BasicBankDetailsResponse> swiftCodes) {
        this(countryEntity.getCountryIso2(), countryEntity.getCountryName(), swiftCodes);
    }
}
