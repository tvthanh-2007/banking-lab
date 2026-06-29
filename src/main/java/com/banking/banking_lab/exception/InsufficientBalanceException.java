package com.banking.banking_lab.exception;

public class InsufficientBalanceException extends RuntimeException {

  public InsufficientBalanceException() {
    super("Insufficient balance");
  }

}
