package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {
	
	public int insert(Employee employee);
	
	public ArrayList<Employee> findAll();
	
	public Employee findById(int id);

}
