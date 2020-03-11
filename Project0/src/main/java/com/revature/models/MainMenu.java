package com.revature.models;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	
private Scanner sc = new Scanner(System.in);
	
	public MainMenu()
	{
		super();
	}
	
	public String runBankApp() {
		String option = "";
		
		System.out.println("Welcome to Revature Banking!");
		System.out.println("Please choose one of the following options: ");
		System.out.println("\t1. New customer"
				+ "\n\t2. Existing customer"
				+ "\n\t3. Bank Employee"
				+ "\n\t4. Exit Application");
		
		option = sc.nextLine();
		
		return option;
	}
	
	public Customer newCustomerCreation(ArrayList<Customer> customerList) {
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";
		String phoneNumber = "";
		
		Customer customer;
		
		System.out.println("Please enter your first name: ");
		firstName = sc.nextLine();
		
		System.out.println("Please enter your last name: ");
		lastName = sc.nextLine();
		
		System.out.println("Please enter your phone number: ");
		phoneNumber = sc.nextLine();
				
		System.out.println("Please enter a username: ");
		
		username = sc.nextLine();
		
		while(isDuplicateUsername(username, customerList)) {
			System.out.println("Please enter another username: ");
			username = sc.nextLine();			
		}
		
		
		System.out.println("Please create a password: ");
		password = sc.nextLine();
		
		
		
		customer = new Customer(firstName, lastName, phoneNumber, username, password);
		System.out.println("Congratulations! Account created successfully. " +
							"Please log in to access your account.\n");
		
		return customer;
		
	}
	
	public Customer existingCustomer(ArrayList<Customer> customerList) {
		String username = "";
		String password = "";
		
		System.out.println("Please enter your username: ");
		username = sc.nextLine();
		System.out.println("Please enter your password: ");
		password = sc.nextLine();
		
	
		if (loginSuccessful(username, password, customerList)) {
			System.out.println("Login successful!\n");
			return selectCustomer(username, password, customerList);
		}
		else {
			System.out.println("Credentials are not correct. Please try again.\n");
			return null;
		}
					
	}
	
	//Method to test if username is unique. Returns true if username already exists
	//or false if it doesn't
	public boolean isDuplicateUsername(String username, ArrayList<Customer> customerList) {
		for (Customer c : customerList) {
			if (username.equals(c.getUsername())) {
				System.out.println("Username already exists.");
				return true;
			}
		}
		
		return false;
	}
	
	public boolean loginSuccessful(String username, String password, ArrayList<Customer> customerList) {
		
		for(Customer c : customerList) {
			if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}

	public Scanner getSc() {
		return sc;
	}
	public void closeSc() {
		this.sc.close();
	}
	

	public Customer selectCustomer(String username, String password, ArrayList<Customer> customerList) {
		for(Customer c : customerList) {
			if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
				return c;
			}
		}
		
		return null;
	}
	
	public Employee bankEmployee(ArrayList<Employee> employeeList) {
		
		String username = "";
		String password = "";
		
		System.out.println("Please enter your username: ");
		username = sc.nextLine();
		System.out.println("Please enter your password: ");
		password = sc.nextLine();
		
	
		if (loginEmpSuccessful(username, password, employeeList)) {
			System.out.println("Login successful!");
			return selectEmployee(username, password, employeeList);
		}
		else {
			System.out.println("Credentials are not correct. Please try again.");
			return null;
		}
		
	}
	
	public boolean loginEmpSuccessful(String username, String password, ArrayList<Employee> employeeList) {
		
		for(Employee e : employeeList) {
			if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Employee selectEmployee(String username, String password, ArrayList<Employee> employeeList) {
		for(Employee e : employeeList) {
			if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
				return e;
			}
		}
		
		return null;
	}
}
