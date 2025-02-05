package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;

import java.util.List;
import java.util.Optional;

public record HeadquarterResponse(String address, String bankName, String countryISO2, String countryName,
                           boolean isHeadquarter, String swiftCode, Optional<List<BasicBankDetailsResponse>> branches) implements Bank{

    public HeadquarterResponse(BankEntity bankEntity, CountryEntity countryEntity, List<BasicBankDetailsResponse> branches) {
        this(bankEntity.getAddress(), bankEntity.getBankName(), countryEntity.getCountryIso2(), countryEntity.getCountryName(), bankEntity.isHeadquarter(), bankEntity.getSwiftCode(), Optional.of(branches));
    }

}
