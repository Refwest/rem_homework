package com.homework.rem.web.models;

import com.homework.rem.data.entities.BankEntity;

public record DeleteBankRequest(String bankName, String countryISO2) {
}
