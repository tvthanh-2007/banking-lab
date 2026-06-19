package com.banking.banking_lab.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banking.banking_lab.dto.CustomerResponse;
import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.exception.CustomerNotFoundException;
import com.banking.banking_lab.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  // @Override
  // public List<Customer> findAll() {
  //   return customerRepository.findAll();
  // }

  @Override
  public Customer create(String name) {
    return customerRepository.save(new Customer(name));
  }

  @Override
  public Customer findById(Long id) {
    return customerRepository.findById(id).orElseThrow(
      () -> new CustomerNotFoundException(id)
    );
  }

  @Override
  public List<Customer> searchContaining(String keyword) {
    return customerRepository.findByNameContaining(keyword);
  }

  @Override
  public List<Customer> searchExact(String name) {
    return customerRepository.findByName(name);
  }

  @Override
  public List<Customer> searchStartingWith(String prefix) {
    return customerRepository.findByNameStartingWith(prefix);
  }

  @Override
  public List<Customer> searchEndingWith(String suffix) {
    return customerRepository.findByNameEndingWith(suffix);
  }

  @Override
  public List<Customer> searchJPQL(String name) {
    return customerRepository.searchByName(name);
  }

  @Override
  public List<Customer> searchNative(String name) {
    return customerRepository.searchNative(name);
  }

  @Override
  @Transactional
  public void updateCustomerName(Long id, String name) {
    Customer customer = customerRepository.findById(id).orElseThrow();

    customer.setName(name);
  }

  @Override
  public Page<CustomerResponse> findAll(Pageable pageable) {
    return customerRepository.findAll(pageable).map(customer -> new CustomerResponse(
                                                                            customer.getId(),
                                                                            customer.getName()
                                                                    )
                                                   );
  }
}
