package com.homework.rem.data.repository;

import com.homework.rem.data.entities.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {

    BankEntity findBankEntityBySwiftCode(String branchSwiftCodePrefix);

    List<BankEntity> findAllBySwiftCodeIsLikeIgnoreCase(String swiftCode);

    List<BankEntity> findAllByCountryId(Long countryId);

    void deleteBySwiftCodeAndBankNameAndCountryId(String swiftCode, String bankName, Long countryId);
}
