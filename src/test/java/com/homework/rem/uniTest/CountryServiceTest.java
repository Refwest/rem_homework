package com.homework.rem.uniTest;

import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.CountryService;
import com.homework.rem.web.models.CountryRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.homework.rem.service.AdjustVariables.unicodeInput;

public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private BankRepository bankRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNewCountryTest() {
        CountryRequest countryRequest = new CountryRequest(
                "pL",
                "pOLAND"
        );

        CountryEntity targetCountryEntity = new CountryEntity(
                "PL",
                "POLAND"
        );


        when(countryRepository.findByCountryIso2Optional(countryRequest.countryISO2())).thenReturn(null);
        when(countryRepository.save(any(CountryEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CountryEntity countryEntity = countryService.createCountry(countryRequest);

        assertEquals(countryEntity.getCountryIso2(), targetCountryEntity.getCountryIso2());
        assertEquals(countryEntity.getCountryName(), targetCountryEntity.getCountryName());
    }

    @Test
    public void createExistingCountryTest() {
        CountryRequest countryRequest = new CountryRequest(
                "pL",
                "pOLAND"
        );

        CountryEntity targetCountryEntity = new CountryEntity(
                "PL",
                "POLAND"
        );


        when(countryRepository.findByCountryIso2Optional(countryRequest.countryISO2())).thenReturn(Optional.of(targetCountryEntity));
        when(countryRepository.save(any(CountryEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CountryEntity countryEntity = countryService.createCountry(countryRequest);

        assertEquals(countryEntity.getCountryIso2(), targetCountryEntity.getCountryIso2());
        assertEquals(countryEntity.getCountryName(), targetCountryEntity.getCountryName());
    }
}
