package com.sm.tnx.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sm.tnx.entity.AuditLog;
import com.sm.tnx.repositories.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditService {

	private final AuditLogRepository auditLogRepository;

	//even if in above transactions exception occurred, save in audit log table.
	//so we want to create new transaction every time for audit log , so used Propagation.REQUIRES_NEW
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveInAuditTable(String remark, String status) {
		AuditLog audit =  AuditLog.builder().remark(remark.substring(0, 250)).status(status).build();
		auditLogRepository.save(audit);		
	}
}
