package com.banking.banking_lab.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    // =========================
    // 🟢 TEST findAll()
    // =========================
    @Test
    void findAll_success() {

        List<Customer> mockData = List.of(
                new Customer("A"),
                new Customer("B")
        );

        when(customerRepository.findAll())
                .thenReturn(mockData);

        List<Customer> result = customerServiceImpl.findAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
        assertEquals("B", result.get(1).getName());

        verify(customerRepository, times(1)).findAll();
    }

     // =========================
    // 🟡 TEST create()
    // =========================
    @Test
    void create_success() {

        String name = "Nguyen Van A";

        Customer savedCustomer = new Customer(name);

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(savedCustomer);

        Customer result = customerServiceImpl.create(name);

        assertNotNull(result);
        assertEquals(name, result.getName());

        verify(customerRepository, times(1))
                .save(any(Customer.class));
    }

    @Test
    void getCustomer_success() {
        Customer cus = new Customer("John");

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(cus));

        Customer result = customerServiceImpl.findById(1L);

        assertEquals("John", result.getName());
    }

    @Test
    void getCustomer_notFound() {
        when(customerRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerServiceImpl.findById(99L));
    }
}
