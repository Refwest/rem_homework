package com.homework.rem.service;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.web.models.BasicBankDetailsResponse;
import com.homework.rem.web.models.CountryResponse;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final BankRepository bankRepository;

    public CountryService(CountryRepository countryRepository, BankRepository bankRepository) {
        this.countryRepository = countryRepository;
        this.bankRepository = bankRepository;

    }

    public CountryResponse fetchCountry(String countryIso2) {
        CountryEntity countryEntity = Optional.ofNullable(countryRepository.findByCountryIso2(countryIso2)).orElseThrow(NotFoundException::new);
        List<BasicBankDetailsResponse> banks = bankRepository.findAllByCountryId(countryEntity.getId()).stream().map(bankEntity -> new BasicBankDetailsResponse(bankEntity, countryEntity)).collect(Collectors.toList());
        return new CountryResponse(countryEntity, banks);
    }
}
