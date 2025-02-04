package com.homework.rem.service;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import com.homework.rem.web.models.*;
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

    public BankService(BankRepository bankRepository, CountryRepository countryRepository, CountryService countryService) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    public BankResponse fetchBank(String swiftCode) {
        Optional<BankEntity> optionalBank = bankRepository.findBankEntityBySwiftCode(swiftCode);
        BankEntity bankEntity = optionalBank.orElseThrow(NotFoundException::new);
        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
        if (bankEntity.isHeadquarter()) {
            List<BasicBankDetailsResponse> branches = fetchBranches(swiftCode);
            return new BankResponse(bankEntity, countryEntity, branches);
        } else {
            return new BankResponse(bankEntity, countryEntity);
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
        BankEntity existingBank = bankRepository.findBankEntityBySwiftCode(bankRequest.swiftCode().toUpperCase()).orElse(null);
        if (existingBank == null) {
            CountryEntity countryEntity = countryService.createCountry(new CountryRequest(bankRequest));
            BankEntity bankEntity = new BankEntity(bankRequest.address().toUpperCase(), bankRequest.bankName().toUpperCase(), bankRequest.isHeadquarter(), bankRequest.swiftCode().toUpperCase(), countryEntity.getId());
            bankRepository.save(bankEntity);
            return new MessageResponse("Bank facility created");
        } else {
            return new MessageResponse("Bank with this SWIFT code exists");
        }
    }

    @Transactional
    public MessageResponse deleteBank(String swiftCode, DeleteBankRequest deleteBankRequest) {
        Optional<CountryEntity> optionalCountry = countryRepository.findByCountryIso2(deleteBankRequest.countryISO2());
        Long countryId = optionalCountry.map(CountryEntity::getId).orElseThrow(NotFoundException::new);
        bankRepository.deleteBySwiftCodeAndBankNameAndCountryId(swiftCode, deleteBankRequest.bankName(), countryId);
        return new MessageResponse("SWIFT code \"" + swiftCode + "\" data deleted");
    }


    //    public BankResponse fetchBank(String swiftCode) {
//        BankEntity bankEntity = Optional.ofNullable(bankRepository.findBankEntitiesBySwiftCode(swiftCode)).orElseThrow(NotFoundException::new);
//        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
//        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
//        return new BankResponse(bankEntity, countryEntity);
//    }

//
//    public HeadquarterResponse fetchHeadquarter(String headquarterSwiftCode) {
//        BankEntity bankEntity = Optional.ofNullable(bankRepository.findBankEntitiesBySwiftCode(headquarterSwiftCode)).orElseThrow(NotFoundException::new);
//        Optional<CountryEntity> optionalCountry = countryRepository.findById(bankEntity.getCountryId());
//        CountryEntity countryEntity = optionalCountry.orElseThrow(NotFoundException::new);
//        List<BasicBankDetailsResponse> branches = fetchBranches(headquarterSwiftCode);
//        return new HeadquarterResponse(bankEntity, countryEntity, branches);
//    }

//    public void classifyBank(String swiftCode) {
//        if (swiftCode.get) {
//            fetchHeadquarter(swiftCode);
//        } else {
//            fetchBank(swiftCode);
//        }
//    }


}
