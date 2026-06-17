package com.banking.banking_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_lab.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
