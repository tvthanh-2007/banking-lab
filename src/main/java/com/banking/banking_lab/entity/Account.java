package com.banking.banking_lab.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.banking.banking_lab.exception.InsufficientBalanceException;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String accountNumber;

	@Column(nullable = false)
	private BigDecimal balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Account() {
	}

	public Account(String accountNumber, BigDecimal balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void deposit(BigDecimal amount) {

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {

			throw new IllegalArgumentException("Amount must be positive");
		}

		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount) {

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {

			throw new IllegalArgumentException("Amount must be positive");

		}

		if (this.balance.compareTo(amount) < 0) {

			throw new InsufficientBalanceException();

		}

		this.balance = this.balance.subtract(amount);

	}
}
