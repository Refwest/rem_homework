package com.homework.rem.service;

import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.web.models.BasicBankDetailsResponse;
import com.homework.rem.web.models.CountryRequest;
import com.homework.rem.web.models.CountryResponse;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.homework.rem.service.AdjustVariables.unicodeInput;

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
        Optional<CountryEntity> optionalCountry = countryRepository.findByCountryIso2Optional(countryIso2);
        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
        List<BasicBankDetailsResponse> swiftCodes = bankRepository.findAllByCountryId(countryEntity.getId()).stream().map(bankEntity -> new BasicBankDetailsResponse(bankEntity, countryEntity)).collect(Collectors.toList());
        return new CountryResponse(countryEntity, swiftCodes);
    }

    @Transactional
    public CountryEntity createCountry(CountryRequest countryRequest) {
        String countryIso2 = unicodeInput(countryRequest.countryISO2());
        String countryName = unicodeInput(countryRequest.countryName());
        CountryEntity existingCountry = countryRepository.findByCountryIso2(countryIso2);

        if (existingCountry == null) {
            CountryEntity countryEntity = new CountryEntity(countryIso2, countryName);
            return countryRepository.save(countryEntity);
        } else {
            return existingCountry;
        }
    }

}
