package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {
	
	public int insert(Account a);
	
	public boolean transfer(Account source, int targetID, double amount);
	
	public ArrayList<Account> findAll();
	
	public boolean deposit(Account a);
	
	public boolean withdraw(Account a);
	
	public boolean changeStatus(Account a);
	
	public boolean delete(Account a);
	
	public ArrayList<Account> findByOwner(Customer customer);
	

}
