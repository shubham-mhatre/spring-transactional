package com.sm.tnx.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sm.tnx.dto.EmployeDto;
import com.sm.tnx.entity.Address;
import com.sm.tnx.entity.Employee;
import com.sm.tnx.repositories.AddressRepository;
import com.sm.tnx.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebService {
	
	private final EmployeeRepository employeeRepository;
	private final AddressRepository addressRepository;
	private final AuditService auditService;
	private final UserIdentityService identityService;

	@Transactional(propagation = Propagation.REQUIRED)
	public Employee saveEmployee(EmployeDto employeeDto) {
		Employee employee = null;
		try {
			employee=saveEmployeeToDb(employeeDto);//save data in employee table
			saveAddressToDb(employeeDto);//save data in address table
			auditService.saveInAuditTable("employee data saved. Employee id : " + employee.getEmpId(),"success");
		}catch(Exception e) {
			System.out.println("exception occured : save in audit log as failed status");
			auditService.saveInAuditTable("employee not data saved. exception occured" + e.getMessage(),"failed");
		}
		
		identityService.validateUserIdentityProofs(employee.getEmpId());
		return employee;		
	}	

	//save data in employee table
	private Employee saveEmployeeToDb(EmployeDto employeeDto) {
		Employee employee = Employee.builder().email(employeeDto.getEmail()).gender(employeeDto.getGender())
				.name(employeeDto.getName()).number(employeeDto.getNumber()).build();		
		return employeeRepository.save(employee);		
	}
	
	//save data in address table
	private void saveAddressToDb(EmployeDto employeeDto) {
		Address address =Address.builder().addr1(employeeDto.getAddr1()).addr2(employeeDto.getAddr2())
				.city(employeeDto.getCity()).state(employeeDto.getState()).build();		
		addressRepository.save(address);		
	}
}
