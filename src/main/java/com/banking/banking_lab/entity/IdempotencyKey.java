package com.banking.banking_lab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "idempotency_keys", uniqueConstraints = { @UniqueConstraint(columnNames = "key") })
public class IdempotencyKey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String key;

  private Long transactionId;

  public IdempotencyKey() {
  }

  public IdempotencyKey(String key, Long transactionId) {

    this.key = key;
    this.transactionId = transactionId;

  }

  public Long getTransactionId() {
    return transactionId;
  }

}
