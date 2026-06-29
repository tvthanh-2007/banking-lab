package com.banking.banking_lab.mapper;

import org.springframework.stereotype.Component;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.entity.Transaction;

@Component
public class TransactionMapper {

  public TransactionResponse map(Transaction tx) {

    return new TransactionResponse(
        tx.getId(),
        tx.getFromAccount().getId(),
        tx.getToAccount().getId(),
        tx.getAmount(),
        tx.getType(),
        tx.getCreatedAt());

  }

}
