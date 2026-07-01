package com.banking.banking_lab.service;

public interface AuditService {
  void saveAudit(String action, String message);
}
