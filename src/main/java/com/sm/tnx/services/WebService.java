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
	private final NotificationService notificationService;

	
	/**
	 * implemented like this, coz we need to call propagation never method outside of transaction
	 * 1st step, save Employee in db
	 * 
	 * 2nd step, send notification to employee
	 */
	public Employee onboardEmployee(EmployeDto employeeDto) {
		Employee employee=saveEmployee(employeeDto);//propagation required		
		sendNotification();//propagation never
		return employee;
	}
	
	/**
	 * create required propagation transaction
	 * save in employee table with.
	 * save in address table
	 * 
	 * create required_new propagation transaction to save in audit log table
	 * create mandatory propagation to save in identitylog table. //comment it when testing for never propagation, implemented as part of notification
	 */
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
		
		//identityService.validateUserIdentityProofs(employee.getEmpId());//madatory propagation
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
	
	//Propagation never
	public void sendNotification() {
		notificationService.sendNotification();		
	}
}
