package com.banking.banking_lab.dto;

import java.math.BigDecimal;

public class AccountResponse {

  private Long id;

  private String accountNumber;

  private BigDecimal balance;

  private Long customerId;

  public AccountResponse(Long id, String accountNumber, BigDecimal balance, Long customerId) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.customerId = customerId;
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

  public Long getCustomerId() {
    return customerId;
  }

}
