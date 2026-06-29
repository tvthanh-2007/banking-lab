package com.banking.banking_lab.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAccountRequest {

  @NotBlank
  private String accountNumber;

  @NotNull
  private BigDecimal balance;

  @NotNull
  private Long customerId;

  public String getAccountNumber() {
    return accountNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

}
