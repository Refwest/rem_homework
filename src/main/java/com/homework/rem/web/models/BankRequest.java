package com.homework.rem.web.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BankRequest(

        String address,
        @NotNull(message = "BANK NAME should not be empty.")
        @NotEmpty(message = "BANK NAME should not be empty.")
        String bankName,
        @NotNull(message = "COUNTRY ISO2 should not be empty.")
        @NotEmpty(message = "COUNTRY ISO2 should not be empty.")
        String countryISO2,
        @NotNull(message = "COUNTRY NAME should not be empty.")
        @NotEmpty(message = "COUNTRY NAME should not be empty.")
        String countryName,

        boolean isHeadquarter,

        @NotNull(message = "SWIFT CODE should not be empty.")
        @NotEmpty(message = "SWIFT CODE should not be empty.")
        String swiftCode) {
}
