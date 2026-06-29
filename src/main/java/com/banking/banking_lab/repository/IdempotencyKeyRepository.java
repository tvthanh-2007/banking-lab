package com.banking.banking_lab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_lab.entity.IdempotencyKey;

public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, Long> {

  Optional<IdempotencyKey> findByKey(String key);

}
