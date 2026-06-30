package com.banking.banking_lab.service;

import org.springframework.stereotype.Service;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.dto.TransferRequest;
import com.banking.banking_lab.entity.Account;
import com.banking.banking_lab.entity.IdempotencyKey;
import com.banking.banking_lab.entity.Transaction;
import com.banking.banking_lab.mapper.TransactionMapper;
import com.banking.banking_lab.repository.AccountRepository;
import com.banking.banking_lab.repository.IdempotencyKeyRepository;
import com.banking.banking_lab.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferServiceImpl implements TransferService {

  private final AccountRepository accountRepository;

  private final TransactionRepository transactionRepository;

  private final IdempotencyKeyRepository idempotencyKeyRepository;

  private final TransactionMapper transactionMapper;

  public TransferServiceImpl(
      AccountRepository accountRepository,
      TransactionRepository transactionRepository,
      IdempotencyKeyRepository idempotencyKeyRepository,
      TransactionMapper transactionMapper) {

    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
    this.idempotencyKeyRepository = idempotencyKeyRepository;
    this.transactionMapper = transactionMapper;

  }

  @Override
  @Transactional
  public TransactionResponse transfer(String key, TransferRequest request) {

    var existing = idempotencyKeyRepository.findByKey(key);

    if (existing.isPresent()) {
      Transaction oldTransaction = transactionRepository
          .findById(existing.get().getTransactionId())
          .orElseThrow();

      return transactionMapper.map(oldTransaction);
    }

    Account fromAccount = accountRepository.findByIdForUpdate(request.getFromAccountId()).orElseThrow();

    Account toAccount = accountRepository.findByIdForUpdate(request.getToAccountId()).orElseThrow();

    // Account fromAccount =
    // accountRepository.findById(request.getFromAccountId()).orElseThrow();

    // Account toAccount =
    // accountRepository.findById(request.getToAccountId()).orElseThrow();

    fromAccount.withdraw(request.getAmount());

    toAccount.deposit(request.getAmount());

    Transaction transaction = new Transaction(
        fromAccount,
        toAccount,
        request.getAmount(),
        "TRANSFER");

    transactionRepository.save(transaction);

    idempotencyKeyRepository.save(new IdempotencyKey(key, transaction.getId()));

    return transactionMapper.map(transaction);
  }
}
