package com.revature.repository;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int id);
}
