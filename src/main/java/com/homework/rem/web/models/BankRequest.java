package com.homework.rem.web.models;

public record BankRequest(String address, String bankName, String countryISO2, String countryName,
                          boolean isHeadquarter, String swiftCode) {
}
