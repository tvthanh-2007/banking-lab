package com.banking.banking_lab.service;

import java.util.List;

import com.banking.banking_lab.entity.Customer;

public interface CustomerService {
  List<Customer> findAll();

  Customer create(String name);

  Customer findById(Long id);

  List<Customer> searchContaining(String keyword);

  List<Customer> searchExact(String name);

  List<Customer> searchStartingWith(String prefix);

  List<Customer> searchEndingWith(String suffix);

  List<Customer> searchJPQL(String name);

  List<Customer> searchNative(String name);
}
