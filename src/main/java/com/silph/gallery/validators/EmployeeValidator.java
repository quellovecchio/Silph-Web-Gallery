package com.silph.gallery.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silph.gallery.model.Employee;
import com.silph.gallery.services.EmployeeService;

@Component
public class EmployeeValidator implements Validator {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		if (employeeService.loginEmployee((Employee)target))
			errors.reject("authenticationFailed");
	}
	
}
