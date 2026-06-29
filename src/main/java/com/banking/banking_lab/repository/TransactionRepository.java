package com.banking.banking_lab.repository;

import com.banking.banking_lab.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
