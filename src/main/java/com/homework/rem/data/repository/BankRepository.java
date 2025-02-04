package com.homework.rem.data.repository;

import com.homework.rem.data.entities.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {

    Optional<BankEntity> findBankEntityBySwiftCode(String branchSwiftCodePrefix);

    List<BankEntity> findAllBySwiftCodeIsLikeIgnoreCaseAndSwiftCodeIsNot(String branchSwiftCodePrefix, String HeadquarterSwiftCode);

    List<BankEntity> findAllByCountryId(Long countryId);

    void deleteBySwiftCodeAndBankNameAndCountryId(String swiftCode, String bankName, Long countryId);
}
