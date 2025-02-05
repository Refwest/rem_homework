package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;

import java.util.List;
import java.util.Optional;

public record BranchResponse(String address, String bankName, String countryISO2, String countryName,
                             boolean isHeadquarter, String swiftCode) implements Bank {

    public BranchResponse(BankEntity bankEntity, CountryEntity countryEntity) {
        this(bankEntity.getAddress(), bankEntity.getBankName(), countryEntity.getCountryIso2(), countryEntity.getCountryName(), bankEntity.isHeadquarter(), bankEntity.getSwiftCode());
    }

}
