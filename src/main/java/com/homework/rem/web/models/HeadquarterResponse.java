package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;

import java.util.List;

public record HeadquarterResponse(String address, String bankName, String countryISO2, String countryName,
                                  boolean isHeadquarter, String swiftCode, List<BranchResponse> branches) {
    public HeadquarterResponse(BankEntity bankEntity, CountryEntity countryEntity, List<BranchResponse> branches) {
        this(bankEntity.getAddress(), bankEntity.getBankName(), bankEntity.getCountryIso2(), countryEntity.getCountryName(), bankEntity.isHeadquarter(), bankEntity.getSwiftCode(), branches);
    }
}

