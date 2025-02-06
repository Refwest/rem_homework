package com.homework.rem.service;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import com.homework.rem.validators.ObjectValidator;
import com.homework.rem.web.models.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private final ObjectValidator<BankRequest> bankRequestObjectValidator;

    public BankService(BankRepository bankRepository, CountryRepository countryRepository, CountryService countryService, ObjectValidator<BankRequest> bankRequestObjectValidator) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
        this.countryService = countryService;
        this.bankRequestObjectValidator = bankRequestObjectValidator;
    }

    public Bank fetchBank(String swiftCode) {
        Optional<BankEntity> optionalBank = bankRepository.findBankEntityBySwiftCode(swiftCode);
        BankEntity bankEntity = optionalBank.orElseThrow(NotFoundException::new);
        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
        if (bankEntity.isHeadquarter()) {
            List<BasicBankDetailsResponse> branches = fetchBranches(swiftCode);
            return new HeadquarterResponse(bankEntity, countryEntity, branches);
        } else {
            return new BranchResponse(bankEntity, countryEntity);
        }
    }

    public List<BasicBankDetailsResponse> fetchBranches(String headquarterSwiftCode) {
        if (headquarterSwiftCode.length() == 11) {
            String branchSwiftCodePrefix = headquarterSwiftCode.substring(0, headquarterSwiftCode.length() - 3) + '%';
            return bankRepository.findAllBySwiftCodeIsLikeIgnoreCaseAndSwiftCodeIsNot(branchSwiftCodePrefix, headquarterSwiftCode)
                    .stream()
                    .map(bankEntity -> {
                        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
                        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
                        return new BasicBankDetailsResponse(bankEntity, countryEntity);
                    }).collect(Collectors.toList());
        } else
            return null;
    }

    @Transactional
    public MessageResponse createBank(BankRequest bankRequest) {
        var violations = bankRequestObjectValidator.validate(bankRequest);

        if (!violations.isEmpty()) {
            return new MessageResponse(String.join("\n", violations));
        } else {
            BankRequest unicodeBankRequest = new BankRequest(
                    unicodeInput(bankRequest.address()),
                    unicodeInput(bankRequest.bankName()),
                    unicodeInput(bankRequest.countryISO2()),
                    unicodeInput(bankRequest.countryName()),
                    bankRequest.isHeadquarter(),
                    unicodeInput(bankRequest.swiftCode()));
            BankEntity existingBank = bankRepository.findBankEntityBySwiftCode(unicodeBankRequest.swiftCode()).orElse(null);
            if (existingBank == null) {
                CountryEntity countryEntity = countryService.createCountry(new CountryRequest(unicodeBankRequest));
                BankEntity bankEntity = new BankEntity(unicodeBankRequest.address(), unicodeBankRequest.bankName(), unicodeBankRequest.isHeadquarter(), unicodeBankRequest.swiftCode(), countryEntity.getId());
                bankRepository.save(bankEntity);
                return new MessageResponse("Bank facility created");
            } else {
                return new MessageResponse("Bank with this SWIFT code exists");
            }
        }
    }

    @Transactional
    public MessageResponse deleteBank(String swiftCode, DeleteBankRequest deleteBankRequest) {
        Optional<CountryEntity> optionalCountry = countryRepository.findByCountryIso2(deleteBankRequest.countryISO2());
        Long countryId = optionalCountry.map(CountryEntity::getId).orElseThrow(NotFoundException::new);
        bankRepository.deleteBySwiftCodeAndBankNameAndCountryId(swiftCode, deleteBankRequest.bankName(), countryId);
        return new MessageResponse("SWIFT code \"" + swiftCode + "\" data deleted");
    }


    public String unicodeInput(String input) {
        if (input == null) {
            return null;
        } else {
            return StringUtils.stripAccents(input).toUpperCase();
        }
    }

}
