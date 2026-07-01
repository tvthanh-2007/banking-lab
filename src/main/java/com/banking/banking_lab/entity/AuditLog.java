package com.banking.banking_lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

	@Id
	@GeneratedValue
	private Long id;

	private String action;

	private String message;

	public AuditLog() {
	}

	public AuditLog(String action, String message) {

		this.action = action;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public String getAction() {
		return action;
	}

	public String getMessage() {
		return message;
	}
}
