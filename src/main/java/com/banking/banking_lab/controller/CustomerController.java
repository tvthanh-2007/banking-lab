package com.banking.banking_lab.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_lab.dto.CreateCustomerRequest;
import com.banking.banking_lab.dto.CustomerResponse;
import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  public List<Customer> getCustomers() {
    return customerService.findAll();
  }

  @PostMapping("/customers")
  public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest req) {
    Customer customer =customerService.create(req.getName());

    return new CustomerResponse(customer.getId(), customer.getName());
  }
}
