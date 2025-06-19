package com.example.csv_reader.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCsvRepresentation {

//    @CsvBindByName(column = "id")
//    private Integer id;

    @CsvBindByName(column = "first_name")
    private String firstName;

    @CsvBindByName(column = "last_name")
    private String lastName;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "gender")
    private String gender;

    @CsvBindByName(column = "ip_address")
    private String ipAddress;

    @CsvBindByName(column = "lane1")
    private String lane1;

    @CsvBindByName(column = "lane2")
    private String lane2;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName(column = "zip_code")
    private String zipcode;

    @CsvBindByName(column = "country")
    private String country;
}
