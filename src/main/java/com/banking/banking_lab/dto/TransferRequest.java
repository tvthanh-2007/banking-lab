package com.banking.banking_lab.dto;

import java.math.BigDecimal;

public class TransferRequest {

  private Long fromAccountId;

  private Long toAccountId;

  private BigDecimal amount;

  public Long getFromAccountId() {
    return fromAccountId;
  }

  public Long getToAccountId() {
    return toAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setFromAccountId(Long id) {
    this.fromAccountId = id;
  }

  public void setToAccountId(Long id) {
    this.toAccountId = id;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

}
