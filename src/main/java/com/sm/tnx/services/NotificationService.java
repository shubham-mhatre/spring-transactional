package com.sm.tnx.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

	@Transactional(propagation = Propagation.NEVER)
	public void sendNotification() {
		System.out.println("Employee onboarded to application mail is sent . . .");
	}
}
