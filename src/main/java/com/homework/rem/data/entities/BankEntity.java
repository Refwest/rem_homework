package com.homework.rem.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @NotNull
    @NotEmpty
    private String bankName;
    @NotNull
    @NotEmpty
    private boolean isHeadquarter;
    @NotNull
    @NotEmpty
    private String swiftCode;
    private Long countryId;

    public BankEntity(String address, String bankName, boolean isHeadquarter, String swiftCode, Long countryId) {
        this.address = address;
        this.bankName = bankName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
        this.countryId = countryId;
    }
}

