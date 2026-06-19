package com.banking.banking_lab.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	// @GetMapping("/customers")
	// public List<Customer> getCustomers() {
	// 	return customerService.findAll();
	// }

	@PostMapping("/customers")
	public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest req) {
		Customer customer = customerService.create(req.getName());

		return new CustomerResponse(customer.getId(), customer.getName());
	}

	@GetMapping("/customers/{id}")
	public CustomerResponse getCustomer(@PathVariable Long id) {
		Customer customer = customerService.findById(id);

		return new CustomerResponse(customer.getId(), customer.getName());
	}

	@GetMapping("/search")
	public List<Customer> search(@RequestParam String keyword) {
		return customerService.searchContaining(keyword);
	}

	@GetMapping("/search/exact")
	public List<Customer> searchExact(@RequestParam String name) {
		return customerService.searchExact(name);
	}

	@GetMapping("/search/start")
	public List<Customer> searchStart(@RequestParam String prefix) {
		return customerService.searchStartingWith(prefix);
	}

	@GetMapping("/search/end")
	public List<Customer> searchEnd(@RequestParam String suffix) {
		return customerService.searchEndingWith(suffix);
	}

	@GetMapping("/search/jpql")
	public List<Customer> searchJPQL(@RequestParam String name) {
		return customerService.searchJPQL(name);
	}

	@GetMapping("/search/native")
	public List<Customer> searchNative(@RequestParam String name) {
		return customerService.searchNative(name);
	}

	@PutMapping("/customers/{id}")
	public void updateCustomer(@PathVariable Long id, @RequestParam String name) {
		customerService.updateCustomerName(id, name);
	}

	@GetMapping("/customers")
	public Page<CustomerResponse> getCustomers(Pageable pageable){
			return customerService.findAll(pageable);
	}
}
