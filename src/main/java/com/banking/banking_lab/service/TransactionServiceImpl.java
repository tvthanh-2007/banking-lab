package com.banking.banking_lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.mapper.TransactionMapper;
import com.banking.banking_lab.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public List<TransactionResponse> findAll() {
    return transactionRepository.findAll()
        .stream()
        .map(tx -> transactionMapper.map(tx))
        .toList();
  }

}
