package com.homework.rem.data.entities;

import jakarta.persistence.*;


public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false)
    private String countryISO2;
    private boolean isHeadquarter;
    @Column(nullable = false, unique = true)
    private String swiftCode;
}
