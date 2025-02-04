package com.homework.rem.web.controllers;

import com.homework.rem.service.CountryService;
import com.homework.rem.web.models.CountryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/swift-codes/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryISO2code}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable String countryISO2code) {
        CountryResponse country = countryService.fetchCountry(countryISO2code);
        return ResponseEntity.ok(country);
    }
}
