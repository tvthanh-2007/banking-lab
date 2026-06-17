package com.banking.banking_lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Customer create(String name) {
    return customerRepository.save(new Customer(name));
  }
}
