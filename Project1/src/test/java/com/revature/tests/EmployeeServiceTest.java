package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeServiceTest {

	private EmployeeService employeeService = null;
	
	@Before
	public void setUp() throws Exception {
		employeeService = new EmployeeService();
	}
	
	@Test
	public void testfindAll() {
		assertNotNull(employeeService.findAll());
	}
	
	@Test
	public void testFindById() {
		assertNotNull(employeeService.findById(1));
	}

}
