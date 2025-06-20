package com.example.csv_reader.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String ip_address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
