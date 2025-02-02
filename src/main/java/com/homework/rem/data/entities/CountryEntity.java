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
    private String CountryIso2;
    private String countryName;
}
