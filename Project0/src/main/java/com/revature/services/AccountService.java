package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOImpl;

public class AccountService {

	AccountDAO repository = null;

	public AccountService() {
		super();
		this.repository = new AccountDAOImpl();
	}

	public AccountService(AccountDAO repository) {
		super();
		this.repository = repository;
	}
	
	public ArrayList<Account> findAll() {
		return repository.findAll();
	}
	
	public int insert(Account a) {
		return repository.insert(a);
	}
	
	public boolean transfer(Account source, int targetID, double amount) {
		return repository.transfer(source, targetID, amount);
	}	
	
	public boolean deposit(Account a) {
		return repository.deposit(a);
	}
	
	public boolean withdraw(Account a) {
		return repository.deposit(a);
	}
	
	public boolean changeStatus(Account a) {
		return repository.changeStatus(a);
	}
	
	public boolean delete(Account a) {
		return repository.delete(a);
		
	}
	
	public ArrayList<Account> findByOwner(Customer customer) {
		return repository.findByOwner(customer);
	}
	
}
