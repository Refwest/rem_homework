package com.homework.rem.integrationTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /v1/swift-codes/{swift-code} returns a BranchResponse - Headquarter")
    public void testGetBankHeadquarter() throws Exception {
        String swiftCode = "BCECCLRMXXX";

        mockMvc.perform(get("/v1/swift-codes/{swift-code}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").value(nullValue()))
                .andExpect(jsonPath("$.bankName").exists())
                .andExpect(jsonPath("$.countryISO2").exists())
                .andExpect(jsonPath("$.countryName").exists())
                .andExpect(jsonPath("$.isHeadquarter").value(true))
                .andExpect(jsonPath("$.swiftCode").value(swiftCode))
                .andExpect(jsonPath("$.branches").exists());
    }

    @Test
    @DisplayName("GET /v1/swift-codes/{swift-code} returns a BranchResponse - Branch")
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
                .andExpect(jsonPath("$", not(hasKey("branches"))));
    }

    @Test
    @DisplayName("POST /v1/swift-codes returns a MessageResponse")
    public void testCreateBank() throws Exception {

        String postJson = """
                {
                "address": "Pawia 17 Kraków, malopolskie, 31-154",
                "bankName": "REM",
                "countryISO2": "US",
                "countryName": "United States",
                "isHeadquarter": true,
                "swiftCode": "ASD123AAA23"
                }
                """;

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("DELETE /v1/swift-codes/{swift-code} returns a MessageResponse")
    public void testDeleteBank() throws Exception {

        String swiftCode = "CITIPLPXBRO";
        String deleteJson = """
                                {
                                    "bankName": "MY BANK",
                                    "countryISO2": "US"
                                }
                """;

        mockMvc.perform(delete("/v1/swift-codes/{swift-code}", swiftCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());
    }
}
