package com.homework.rem.web.controllers;

import com.homework.rem.service.BankService;
import com.homework.rem.web.models.BankResponse;
import com.homework.rem.web.models.DeleteBankRequest;
import com.homework.rem.web.models.DeleteBankResponse;
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
    public ResponseEntity<BankResponse> getBank(@PathVariable(name = "swift-code") String swiftCode) {
        BankResponse bank = bankService.fetchBank(swiftCode);
        return ResponseEntity.ok(bank);
    }

    @DeleteMapping("/{swift-code}")
    public ResponseEntity<DeleteBankResponse> removeBank(@PathVariable(name = "swift-code") String swiftCode, @RequestBody DeleteBankRequest deleteBankRequest) {
        DeleteBankResponse message = bankService.deleteBank(swiftCode, deleteBankRequest);
        return ResponseEntity.status(HttpStatus.GONE).body(message);
    }
}
