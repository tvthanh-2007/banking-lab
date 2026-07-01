package com.banking.banking_lab.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.banking.banking_lab.entity.AuditLog;
import com.banking.banking_lab.repository.AuditLogRepository;

@Service
public class AuditServiceImpl implements AuditService {

	private final AuditLogRepository auditLogRepository;

	public AuditServiceImpl(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveAudit(String action, String message) {
		auditLogRepository.save(new AuditLog(action, message));
	}

}
