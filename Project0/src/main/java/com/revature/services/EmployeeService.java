package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repository.EmployeeDAO;
import com.revature.repository.EmployeeDAOImpl;

public class EmployeeService {
	EmployeeDAO repository = null;

	public EmployeeService() {
		super();
		this.repository = new EmployeeDAOImpl();
	}

	public EmployeeService(EmployeeDAO repository) {
		super();
		this.repository = repository;
	}
	
	public ArrayList<Employee> findAll() {
		return repository.findAll();
	}
	
	public int insert(Employee e) {
		return repository.insert(e);
	}

}
