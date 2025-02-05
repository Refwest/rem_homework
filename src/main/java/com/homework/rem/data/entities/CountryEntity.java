package com.homework.rem.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private String countryIso2;
    @NotNull
    @NotEmpty
    private String countryName;

    public CountryEntity(String countryIso2, String countryName) {
        this.countryIso2 = countryIso2;
        this.countryName = countryName;
    }
}
