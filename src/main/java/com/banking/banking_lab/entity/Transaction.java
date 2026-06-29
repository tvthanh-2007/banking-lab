package com.banking.banking_lab.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_account_id")
  private Account fromAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_account_id")
  private Account toAccount;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private String type;

  private LocalDateTime createdAt;

  public Transaction() {
  }

  public Transaction(Account fromAccount, Account toAccount, BigDecimal amount, String type) {

    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.amount = amount;
    this.type = type;
    this.createdAt = LocalDateTime.now();

  }

  public Long getId() {
    return id;
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

  public Account getFromAccount() {
    return fromAccount;
  }

  public Account getToAccount() {
    return toAccount;
  }

}
