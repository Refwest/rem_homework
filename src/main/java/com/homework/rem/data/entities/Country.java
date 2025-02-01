package com.homework.rem.data.entities;

import jakarta.persistence.*;


public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String CountryISO2;
    @Column(nullable = false, unique = true)
    private String countryName;
    private Bank banks;
}
