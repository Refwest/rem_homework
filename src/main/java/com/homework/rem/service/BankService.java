package com.homework.rem.service;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import com.homework.rem.web.models.BranchResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;

    public BankService(BankRepository bankRepository, CountryRepository countryRepository) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
    }

    public BranchResponse fetchBranch(Long id) {
        Optional<BankEntity> optionalBank = bankRepository.findById(id);
        BankEntity bankEntity = optionalBank.orElseThrow(NotFoundException::new);
        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
        return new BranchResponse(bankEntity, countryEntity);
    }



}
