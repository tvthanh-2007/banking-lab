package com.banking.banking_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_lab.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
