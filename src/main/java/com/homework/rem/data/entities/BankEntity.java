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
}

