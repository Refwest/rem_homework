package com.homework.rem.web.controllers;

import com.homework.rem.service.BankService;
import com.homework.rem.web.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/v1/swift-codes")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{swift-code}")
    public ResponseEntity<Bank> getBank(@PathVariable(name = "swift-code") String swiftCode) {
        Bank bank = bankService.fetchBank(swiftCode);
        return ResponseEntity.ok(bank);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createBank(@RequestBody BankRequest bankRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankService.createBank(bankRequest));
    }

    @DeleteMapping("/{swift-code}")
    public ResponseEntity<MessageResponse> removeBank(@PathVariable(name = "swift-code") String swiftCode, @RequestBody DeleteBankRequest deleteBankRequest) {
        MessageResponse message = bankService.deleteBank(swiftCode, deleteBankRequest);
        return ResponseEntity.ok(message);
    }
}
