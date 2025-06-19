package com.example.csv_reader.repository;

import com.example.csv_reader.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
