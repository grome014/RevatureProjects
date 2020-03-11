package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface CustomerDAO {
	
	public int insert(Customer customer);
	
	public ArrayList<Customer> findAll();
	
	public ArrayList<Customer> findByAccount(Account account);

}
