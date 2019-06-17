package com.silph.gallery.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {

	public List<Employee> findByEmail(String email);
	
}
