package com.sm.tnx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.tnx.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
