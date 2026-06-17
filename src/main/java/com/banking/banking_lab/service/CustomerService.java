package com.banking.banking_lab.service;

import java.util.List;

import com.banking.banking_lab.entity.Customer;

public interface CustomerService {
  List<Customer> findAll();

  Customer create(String name);

  Customer findById(Long id);
}
