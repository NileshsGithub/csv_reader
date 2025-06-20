package com.example.csv_reader.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lane1;
    private String lane2;
    private String city;
    private String zipcode;
    private String country;


}
