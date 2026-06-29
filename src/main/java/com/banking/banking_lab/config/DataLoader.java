package com.banking.banking_lab.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.banking.banking_lab.entity.Account;
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
        Customer customer = new Customer("Thanh");

        Account acc1 = new Account("111111", new BigDecimal("1000"));
        Account acc2 = new Account("222222", new BigDecimal("5000"));

        customer.addAccount(acc1);
        customer.addAccount(acc2);

        customerRepository.save(customer);
    }
}
