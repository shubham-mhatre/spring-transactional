package com.sm.tnx.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sm.tnx.entity.UserIdentityLogs;
import com.sm.tnx.repositories.UserIdentityLogsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserIdentityService {
	
	private final UserIdentityLogsRepository identityLogsRepository;

	//propagation mandatory will use existing transaction, if it don't find any existing transaction it will throw exception
	@Transactional(propagation = Propagation.MANDATORY)
	public void validateUserIdentityProofs(Long userId) {
		
		try {
			validateUserProof();
			//successful identity proof validation
			UserIdentityLogs userIdentityLogs=UserIdentityLogs.builder().
					remark("user identity verified")
					.status("success")//user_id
					.empId(userId)
					.build();	
			identityLogsRepository.save(userIdentityLogs);
		}catch(Exception e) {			
			//exception identity proof validation
			UserIdentityLogs userIdentityLogs=UserIdentityLogs.builder().
					remark("identity validation failed")
					.status("failed")//user_id
					.empId(userId)
					.build();
			identityLogsRepository.save(userIdentityLogs);
		}		
	}
	//based on user identity proofs, return true or false.
	private boolean validateUserProof() {
		throw new RuntimeException("user proof validation failed");
		//return true;
	}
}
