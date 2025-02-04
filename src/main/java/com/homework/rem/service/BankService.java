package com.homework.rem.service;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.exception.NotFoundException;
import com.homework.rem.web.models.BankResponse;
import com.homework.rem.web.models.BasicBankDetailsResponse;
//import com.homework.rem.web.models.HeadquarterResponse;
import com.homework.rem.web.models.DeleteBankRequest;
import com.homework.rem.web.models.DeleteBankResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;

    public BankService(BankRepository bankRepository, CountryRepository countryRepository) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
    }

    public BankResponse fetchBank(String swiftCode) {
        BankEntity bankEntity = Optional.ofNullable(bankRepository.findBankEntityBySwiftCode(swiftCode)).orElseThrow(NotFoundException::new);
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
            System.out.println(branchSwiftCodePrefix);
            return bankRepository.findAllBySwiftCodeIsLikeIgnoreCase(branchSwiftCodePrefix)
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
    public DeleteBankResponse deleteBank(String swiftCode, DeleteBankRequest deleteBankRequest) {
        CountryEntity countryEntity = Optional.ofNullable(countryRepository.findByCountryIso2(deleteBankRequest.countryISO2())).orElseThrow(NotFoundException::new);
        bankRepository.deleteBySwiftCodeAndBankNameAndCountryId(swiftCode, deleteBankRequest.bankName(), countryEntity.getId());
        return new DeleteBankResponse("SWIFT code \"" + swiftCode + "\" data deleted");
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
