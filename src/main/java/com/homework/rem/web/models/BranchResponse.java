package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;

public record BranchResponse(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode) {
    public BranchResponse(BankEntity bankEntity, CountryEntity countryEntity) {
        this(bankEntity.getAddress(), bankEntity.getBankName(), bankEntity.getCountryIso2(), countryEntity.getCountryName(), bankEntity.isHeadquarter(), bankEntity.getSwiftCode());
    }
}
