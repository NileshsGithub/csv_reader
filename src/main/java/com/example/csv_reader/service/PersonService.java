package com.example.csv_reader.service;

import com.example.csv_reader.dto.PersonCsvRepresentation;
import com.example.csv_reader.entity.Address;
import com.example.csv_reader.entity.Person;
import com.example.csv_reader.repository.PersonRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Integer uploadPerson(MultipartFile file) throws IOException {
        Set<Person> persons = parseCsv(file);
        personRepository.saveAll(persons);
        return persons.size();
    }

    private Set<Person> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<PersonCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(PersonCsvRepresentation.class);

            CsvToBean<PersonCsvRepresentation> csvToBean = new CsvToBeanBuilder<PersonCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse().stream()
                    .map(csvLine -> {
                        Address address = new Address();
                        address.setLane1(csvLine.getLane1());
                        address.setLane2(csvLine.getLane2());
                        address.setCity(csvLine.getCity());
                        address.setZipcode(csvLine.getZipcode());
                        address.setCountry(csvLine.getCountry());

                        return Person.builder()
                                .first_name(csvLine.getFirstName())
                                .last_name(csvLine.getLastName())
                                .email(csvLine.getEmail())
                                .gender(csvLine.getGender())
                                .ip_address(csvLine.getIpAddress())
                                .address(address)
                                .build();
                    })
                    .collect(Collectors.toSet());
        }
    }

    public int loadFromClasspath(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            Set<Person> persons = parseReader(reader);
            personRepository.saveAll(persons);
            return persons.size();
        }
    }

    public Set<Person> parseReader(Reader reader) {
        HeaderColumnNameMappingStrategy<PersonCsvRepresentation> strategy =
                new HeaderColumnNameMappingStrategy<>();
        strategy.setType(PersonCsvRepresentation.class);

        CsvToBean<PersonCsvRepresentation> csvToBean =
                new CsvToBeanBuilder<PersonCsvRepresentation>(reader)
                .withMappingStrategy(strategy)
                .withIgnoreEmptyLine(true)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse().stream()
                .map(csv -> {
                    Address address = new Address();
                    address.setLane1(csv.getLane1());
                    address.setLane2(csv.getLane2());
                    address.setCity(csv.getCity());
                    address.setZipcode(csv.getZipcode());
                    address.setCountry(csv.getCountry());

                    return Person.builder()
                            .first_name(csv.getFirstName())
                            .last_name(csv.getLastName())
                            .email(csv.getEmail())
                            .gender(csv.getGender())
                            .ip_address(csv.getIpAddress())
                            .address(address)
                            .build();
                })
                .collect(Collectors.toSet());
    }
}
