package com.sm.tnx.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendationService {

	/**
	 * not supported propagation will execute always, if it found existing transaction, it will suspend it and execute,
	 * otherwise directly execute
	 * 
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getRecommendations() {
		System.out.println("as Employee is onboarded, it is recommended to complete Introduction & POSH Course");
		return "as Employee is onboarded, it is recommended to complete Introduction & POSH Course";
	}
}
