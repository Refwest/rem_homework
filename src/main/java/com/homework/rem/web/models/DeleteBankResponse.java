package com.homework.rem.web.models;

public record DeleteBankResponse(String message) {
    public DeleteBankResponse(String message) {
        this.message = message;
    }
}
