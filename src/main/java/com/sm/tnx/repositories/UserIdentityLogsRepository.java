package com.sm.tnx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sm.tnx.entity.UserIdentityLogs;

@Repository
public interface UserIdentityLogsRepository extends JpaRepository<UserIdentityLogs, Integer> {

}
