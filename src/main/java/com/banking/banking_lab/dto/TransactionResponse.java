package com.banking.banking_lab.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

  private Long id;

  private Long fromAccountId;

  private Long toAccountId;

  private BigDecimal amount;

  private String type;

  private LocalDateTime createdAt;

  public TransactionResponse(
      Long id,
      Long fromAccountId,
      Long toAccountId,
      BigDecimal amount,
      String type,
      LocalDateTime createdAt) {

    this.id = id;
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
    this.type = type;
    this.createdAt = createdAt;

  }

  public Long getId() {
    return id;
  }

  public Long getFromAccountId() {
    return fromAccountId;
  }

  public Long getToAccountId() {
    return toAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getType() {
    return type;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

}
