package com.banking.banking_lab.service;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.dto.TransferRequest;

public interface TransferService {

  TransactionResponse transfer(String key, TransferRequest request);

}
