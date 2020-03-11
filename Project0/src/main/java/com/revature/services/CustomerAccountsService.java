package com.revature.services;

import com.revature.repository.CustomerAccountsDAO;
import com.revature.repository.CustomerAccountsDAOImpl;

public class CustomerAccountsService {
	
	CustomerAccountsDAO repository = null;
	
	public CustomerAccountsService() {
		super();
		this.repository = new CustomerAccountsDAOImpl();
	}

	public CustomerAccountsService(CustomerAccountsDAO repository) {
		super();
		this.repository = repository;
	}
	
	public boolean insert(int customerID, int accountID) {
		return repository.insert(customerID, accountID);
	}

}
