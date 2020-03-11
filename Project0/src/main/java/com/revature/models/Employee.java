package com.revature.models;

import java.util.Objects;

public class Employee extends User {
	
	private int employeeID;
	private Role role;
	
	public Employee() {
		super();
	}
	
	public Employee(String firstName, String lastName, String phoneNumber, String username, String password,
			Role role, int employeeID) {
		super(firstName, lastName, phoneNumber, username, password);
		this.role = role;
		this.employeeID = employeeID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(employeeID, role);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return employeeID == other.employeeID && role == other.role;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", role=" + role + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getPhoneNumber()=" + getPhoneNumber() + "]";
	}
	
	
}
