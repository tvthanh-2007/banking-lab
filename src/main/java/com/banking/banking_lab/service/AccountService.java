package com.banking.banking_lab.service;

import com.banking.banking_lab.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

  AccountResponse create(CreateAccountRequest request);

  AccountResponse findById(Long id);

  List<AccountResponse> findAll();

  AccountResponse deposit(Long id, BigDecimal amount);

  AccountResponse withdraw(Long id, BigDecimal amount);

}
