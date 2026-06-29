package com.banking.banking_lab.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_lab.dto.AccountResponse;
import com.banking.banking_lab.dto.CreateAccountRequest;
import com.banking.banking_lab.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  public AccountResponse create(@Valid @RequestBody CreateAccountRequest request) {
    return accountService.create(request);
  }

  @GetMapping
  public List<AccountResponse> findAll() {
    return accountService.findAll();
  }

  @GetMapping("/{id}")
  public AccountResponse findById(@PathVariable Long id) {
    return accountService.findById(id);
  }

  @PostMapping("/{id}/deposit")
  public AccountResponse deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
    return accountService.deposit(id, amount);
  }

  @PostMapping("/{id}/withdraw")
  public AccountResponse withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
    return accountService.withdraw(id, amount);
  }
}
