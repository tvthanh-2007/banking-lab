package com.banking.banking_lab.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.banking_lab.dto.AccountResponse;
import com.banking.banking_lab.dto.CreateAccountRequest;
import com.banking.banking_lab.entity.Account;
import com.banking.banking_lab.entity.Customer;
import com.banking.banking_lab.repository.AccountRepository;
import com.banking.banking_lab.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	private final CustomerRepository customerRepository;

	public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public AccountResponse create(CreateAccountRequest request) {

		Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

		Account account = new Account(
				request.getAccountNumber(),
				request.getBalance());

		account.setCustomer(customer);

		return map(accountRepository.save(account));
	}

	@Override
	public AccountResponse findById(Long id) {

		Account account = accountRepository.findById(id).orElseThrow();
		return map(account);

	}

	@Override
	public List<AccountResponse> findAll() {

		return accountRepository.findAll()
				.stream()
				.map(this::map)
				.toList();

	}

	@Override
	@Transactional
	public AccountResponse deposit(Long id, BigDecimal amount) {

		Account account = accountRepository.findById(id).orElseThrow();

		account.deposit(amount);

		return map(account);

	}

	@Override
	@Transactional
	public AccountResponse withdraw(Long id, BigDecimal amount) {
		Account account = accountRepository.findById(id).orElseThrow();

		account.withdraw(amount);

		return map(account);
	}

	private AccountResponse map(Account account) {
		return new AccountResponse(
				account.getId(),
				account.getAccountNumber(),
				account.getBalance(),
				account.getCustomer().getId());

	}
}
