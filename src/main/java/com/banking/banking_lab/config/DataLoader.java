package com.banking.banking_lab.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.repository.CustomerRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {

        if (customerRepository.count() == 0) {
            customerRepository.save(new Customer("Thanh"));
            customerRepository.save(new Customer("Alice"));
        }
    }
}
