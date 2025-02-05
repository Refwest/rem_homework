package com.homework.rem.integrationTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /v1/swift-codes/{swift-code} returns a BankResponse - Headquarter")
    public void testGetBankHeadquarter() throws Exception {
        String swiftCode = "BCECCLRMXXX";

        mockMvc.perform(get("/v1/swift-codes/{swift-code}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.bankName").exists())
                .andExpect(jsonPath("$.countryISO2").exists())
                .andExpect(jsonPath("$.countryName").exists())
                .andExpect(jsonPath("$.isHeadquarter").value(true))
                .andExpect(jsonPath("$.swiftCode").value(swiftCode))
                .andExpect(jsonPath("$.branches").exists());
    }

    @Test
    @DisplayName("GET /v1/swift-codes/{swift-code} returns a BankResponse - Branch")
    public void testGetBankBranch() throws Exception {

        String swiftCode = "CITIPLPXBRO";

        mockMvc.perform(get("/v1/swift-codes/{swift-code}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.bankName").exists())
                .andExpect(jsonPath("$.countryISO2").exists())
                .andExpect(jsonPath("$.countryName").exists())
                .andExpect(jsonPath("$.isHeadquarter").value(false))
                .andExpect(jsonPath("$.swiftCode").value(swiftCode))
                .andExpect(jsonPath("$.branches").exists());
    }
}
