package com.banking.banking_lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.banking_lab.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByNameContaining(String keyword);

    List<Customer> findByNameStartingWith(String prefix);

    List<Customer> findByNameEndingWith(String suffix);

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> searchByName(String name);

    @Query(value = "SELECT * FROM customers WHERE name = :name", nativeQuery = true)
    List<Customer> searchNative(String name);
}
