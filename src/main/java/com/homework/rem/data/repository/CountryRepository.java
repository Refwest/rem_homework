package com.homework.rem.data.repository;

import com.homework.rem.data.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    Optional<CountryEntity> findByCountryIso2Optional(String CountryIso2);
    CountryEntity findByCountryIso2(String CountryIso2);
}
