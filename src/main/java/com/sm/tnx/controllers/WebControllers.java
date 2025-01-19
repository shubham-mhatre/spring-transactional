package com.sm.tnx.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sm.tnx.dto.EmployeDto;
import com.sm.tnx.entity.Employee;
import com.sm.tnx.services.WebService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebControllers {
	
	private final WebService webService;

	@PostMapping(value="/saveEmployee")
	public Employee saveEmployee(@RequestBody EmployeDto employeeDto) {
		Employee employee= webService.saveEmployee(employeeDto);
		return employee;
	}
}
