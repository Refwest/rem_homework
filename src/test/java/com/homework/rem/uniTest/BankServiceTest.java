package com.homework.rem.uniTest;

import com.homework.rem.data.entities.BankEntity;
import com.homework.rem.data.entities.CountryEntity;
import com.homework.rem.data.repository.BankRepository;
import com.homework.rem.data.repository.CountryRepository;
import com.homework.rem.service.BankService;
import com.homework.rem.service.CountryService;
import com.homework.rem.validators.ObjectValidator;
import com.homework.rem.web.models.BankRequest;
import com.homework.rem.web.models.MessageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BankServiceTest {
    private BankService bankService;
    private BankRepository bankRepository;
    private CountryRepository countryRepository;
    private CountryService countryService;
    private ObjectValidator<BankRequest> bankRequestObjectValidator;

    @BeforeEach
    void setUp() {
        bankRepository = mock(BankRepository.class);
        countryRepository = mock(CountryRepository.class);
        countryService = mock(CountryService.class);
        bankRequestObjectValidator = mock(ObjectValidator.class);

        bankService = new BankService(bankRepository, countryRepository, countryService, bankRequestObjectValidator);
    }

    @Test
    public void createBankTest() {

        BankRequest bankRequest = new BankRequest(
                "Pawia 17 Kraków, malopolskie, 31-154",
                "REM",
                "PL",
                "POLSKA",
                false,
                "ASD1AABAA23");

        CountryEntity countryEntity = new CountryEntity("PL", "POLAND");
        countryEntity.setId(12L);

        when(countryService.createCountry(any()))
                .thenReturn(countryEntity);

        MessageResponse messageResponse = bankService.createBank(bankRequest);

        assertEquals("Bank facility created", messageResponse.message());
    }
}
