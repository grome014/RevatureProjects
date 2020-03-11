package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repository.CustomerDAO;
import com.revature.repository.CustomerDAOImpl;

public class CustomerService {
	CustomerDAO repository = null;

	public CustomerService() {
		super();
		this.repository = new CustomerDAOImpl();
	}

	public CustomerService(CustomerDAO repository) {
		super();
		this.repository = repository;
	}
	
	public ArrayList<Customer> findAll() {
		return repository.findAll();
	}
	
	public int insert(Customer c) {
		return repository.insert(c);
	}
	
	public ArrayList<Customer> findByAccount(Account account) {
		return repository.findByAccount(account);
	}

}
