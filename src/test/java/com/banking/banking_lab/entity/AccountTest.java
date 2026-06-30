package com.banking.banking_lab.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void create_account_should_have_balance() {

    Account account = new Account("111", BigDecimal.valueOf(1000));

    assertEquals(BigDecimal.valueOf(1000), account.getBalance());

  }

  @Test
  void new_account_version_should_be_null() {

    Account account = new Account("111", BigDecimal.valueOf(1000));

    assertNull(account.getVersion());

  }

}
