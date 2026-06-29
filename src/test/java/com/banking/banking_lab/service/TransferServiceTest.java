package com.banking.banking_lab.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.banking.banking_lab.dto.TransactionResponse;
import com.banking.banking_lab.dto.TransferRequest;
import com.banking.banking_lab.entity.Account;
import com.banking.banking_lab.entity.IdempotencyKey;
import com.banking.banking_lab.entity.Transaction;
import com.banking.banking_lab.exception.InsufficientBalanceException;
import com.banking.banking_lab.mapper.TransactionMapper;
import com.banking.banking_lab.repository.AccountRepository;
import com.banking.banking_lab.repository.IdempotencyKeyRepository;
import com.banking.banking_lab.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private IdempotencyKeyRepository idempotencyRepository;

  @Mock
  private TransactionMapper transactionMapper;

  @InjectMocks
  private TransferServiceImpl transferService;

  @Test
  void transfer_success() {

    Account from = new Account("111", BigDecimal.valueOf(1000));

    Account to = new Account("222", BigDecimal.ZERO);

    TransferRequest request = new TransferRequest();

    request.setFromAccountId(1L);
    request.setToAccountId(2L);
    request.setAmount(BigDecimal.valueOf(300));

    when(idempotencyRepository.findByKey("abc"))
        .thenReturn(Optional.empty());

    when(accountRepository.findByIdForUpdate(1L))
        .thenReturn(Optional.of(from));

    when(accountRepository.findByIdForUpdate(2L))
        .thenReturn(Optional.of(to));

    when(transactionRepository.save(any(Transaction.class)))
        .thenAnswer(i -> i.getArgument(0));

    when(transactionMapper.map(any(Transaction.class)))
        .thenReturn(mockResponse());

    transferService.transfer("abc", request);

    assertEquals(BigDecimal.valueOf(700), from.getBalance());

    assertEquals(BigDecimal.valueOf(300), to.getBalance());

    verify(transactionRepository).save(any(Transaction.class));

    verify(idempotencyRepository).save(any(IdempotencyKey.class));

  }

  // =====================================
  // NOT ENOUGH BALANCE
  // =====================================

  @Test
  void transfer_not_enough_balance() {

    Account from = new Account("111", BigDecimal.valueOf(100));

    Account to = new Account("222", BigDecimal.ZERO);

    TransferRequest request = new TransferRequest();

    request.setFromAccountId(1L);
    request.setToAccountId(2L);
    request.setAmount(BigDecimal.valueOf(300));

    when(idempotencyRepository.findByKey("abc"))
        .thenReturn(Optional.empty());

    when(accountRepository.findByIdForUpdate(1L))
        .thenReturn(Optional.of(from));

    when(accountRepository.findByIdForUpdate(2L))
        .thenReturn(Optional.of(to));

    assertThrows(InsufficientBalanceException.class, () -> transferService.transfer("abc", request));

    verify(transactionRepository, never()).save(any());

    verify(idempotencyRepository, never()).save(any());

  }

  // =====================================
  // DUPLICATE IDEMPOTENCY KEY
  // =====================================

  @Test
  void transfer_duplicate_should_not_process_again() {

    TransferRequest request = new TransferRequest();

    request.setFromAccountId(1L);
    request.setToAccountId(2L);
    request.setAmount(BigDecimal.valueOf(300));

    IdempotencyKey key = new IdempotencyKey("abc", 10L);

    Transaction oldTransaction = new Transaction();

    when(idempotencyRepository.findByKey("abc")).thenReturn(Optional.of(key));

    when(transactionRepository.findById(10L)).thenReturn(Optional.of(oldTransaction));

    when(transactionMapper.map(any(Transaction.class))).thenReturn(mockResponse());

    TransactionResponse result = transferService.transfer("abc", request);

    assertNotNull(result);

    verify(accountRepository, never()).findByIdForUpdate(any());

    verify(transactionRepository, never()).save(any());

    verify(idempotencyRepository, never()).save(any());

  }

  // ===============================
  // HELPER
  // ===============================

  private TransactionResponse mockResponse() {

    return new TransactionResponse(
        1L,
        1L,
        2L,
        BigDecimal.valueOf(300),
        "TRANSFER",
        LocalDateTime.now());

  }

}
