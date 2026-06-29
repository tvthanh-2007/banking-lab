package com.banking.banking_lab.service;

import java.util.List;

import com.banking.banking_lab.dto.TransactionResponse;

public interface TransactionService {
  List<TransactionResponse> findAll();
}
