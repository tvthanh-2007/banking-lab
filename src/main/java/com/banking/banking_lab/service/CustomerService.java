package com.banking.banking_lab.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.banking.banking_lab.dto.CustomerResponse;
import com.banking.banking_lab.entity.Customer;

public interface CustomerService {
  // List<Customer> findAll();

  Customer create(String name);

  Customer findById(Long id);

  List<Customer> searchContaining(String keyword);

  List<Customer> searchExact(String name);

  List<Customer> searchStartingWith(String prefix);

  List<Customer> searchEndingWith(String suffix);

  List<Customer> searchJPQL(String name);

  List<Customer> searchNative(String name);

  void updateCustomerName(Long id, String name);

  Page<CustomerResponse> findAll(Pageable pageable);
}
