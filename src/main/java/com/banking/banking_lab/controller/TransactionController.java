package com.banking.banking_lab.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {

    this.transactionService = transactionService;

  }

  @GetMapping
  public List<TransactionResponse> findAll() {
    return transactionService.findAll();
  }

}
