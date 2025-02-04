package com.homework.rem.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryIso2;
    private String countryName;

    public CountryEntity(String countryIso2, String countryName) {
        this.countryIso2 = countryIso2;
        this.countryName = countryName;
    }
}
