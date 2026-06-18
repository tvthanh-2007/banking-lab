
package com.banking.banking_lab.controller;

import com.banking.banking_lab.dto.CreateCustomerRequest;
import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.exception.CustomerNotFoundException;
import com.banking.banking_lab.service.CustomerServiceImpl;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // 🟢 GET /customers (LIST)
    // =========================
    @Test
    void getCustomers_success() throws Exception {

        List<Customer> customers = List.of(
                new Customer("A"),
                new Customer("B")
        );

        when(customerServiceImpl.findAll())
                .thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("A"))
                .andExpect(jsonPath("$[1].name").value("B"));
    }

    // =========================
    // 🟡 POST /customers
    // =========================
    @Test
    void createCustomer_success() throws Exception {

        // request body
        CreateCustomerRequest req = new CreateCustomerRequest();
        req.setName("Nguyen Van A");

        // fake service return
        Customer saved = new Customer("Nguyen Van A");

        when(customerServiceImpl.create("Nguyen Van A"))
                .thenReturn(saved);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nguyen Van A"));
    }

    // =========================
    // SUCCESS CASE
    // =========================
    @Test
    void getCustomer_success() throws Exception {

        Customer customer = new Customer("John");

        when(customerServiceImpl.findById(1L))
                .thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    // =========================
    // NOT FOUND CASE
    // =========================
    @Test
    void getCustomer_notFound() throws Exception {

        when(customerServiceImpl.findById(99L))
                .thenThrow(new CustomerNotFoundException(99L));

        mockMvc.perform(get("/customers/99"))
                .andExpect(status().isNotFound());
    }
}
