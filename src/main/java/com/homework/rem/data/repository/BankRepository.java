package com.homework.rem.data.repository;

import com.homework.rem.data.entities.Bank;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
