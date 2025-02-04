package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;

public record BasicBankDetailsResponse(String address, String bankName, String countryISO2, boolean isHeadquarter, String swiftCode) {
    public BasicBankDetailsResponse(BankEntity bankEntity, CountryEntity countryEntity) {
        this(bankEntity.getAddress(), bankEntity.getBankName(), countryEntity.getCountryIso2(), bankEntity.isHeadquarter(), bankEntity.getSwiftCode());
    }
}
