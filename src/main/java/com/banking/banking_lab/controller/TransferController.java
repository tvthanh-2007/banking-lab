package com.banking.banking_lab.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.dto.TransferRequest;
import com.banking.banking_lab.service.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {

  private final TransferService transferService;

  public TransferController(TransferService transferService) {
    this.transferService = transferService;
  }

  @PostMapping
  public TransactionResponse transfer(
      @RequestHeader("Idempotency-Key") String key,
      @RequestBody TransferRequest request) {

    return transferService.transfer(key, request);

  }

}
