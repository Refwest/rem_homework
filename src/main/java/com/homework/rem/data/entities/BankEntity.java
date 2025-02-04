package com.homework.rem.data.entities;

import jakarta.persistence.*;
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
    private String bankName;
    private boolean isHeadquarter;
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

