package com.silph.gallery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silph.gallery.model.Employee;
import com.silph.gallery.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// The Email
	@Transactional
	public Boolean loginEmployee(Employee insertedEmployee) {
		List<Employee> employeeList = employeeRepository.findByEmail(insertedEmployee.getEmail());
		if (employeeList.isEmpty() || employeeList.size() > 1)
			return false;
		Employee e = employeeList.get(0);
		if (e.isPasswordCorrect(insertedEmployee.getPwd())) 
			return true;
		else
			return false;
	}
}
