package com.banking.banking_lab.repository;

import com.banking.banking_lab.entity.Account;

import jakarta.persistence.LockModeType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByAccountNumber(String accountNumber);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("""
          select a
          from Account a
          where a.id = :id
      """)
  Optional<Account> findByIdForUpdate(Long id);
}
