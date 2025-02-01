package com.homework.rem.data.repository;

import com.homework.rem.data.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
