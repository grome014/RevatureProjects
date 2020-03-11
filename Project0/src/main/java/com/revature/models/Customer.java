package com.revature.models;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends User {
	
	private int customerID;
	private ArrayList<Account> accounts;
	
	public Customer() {
		super();
	}
	
	public Customer(String firstName, String lastName, String phoneNumber, String username, String password) {
		super(firstName, lastName, phoneNumber, username, password);
		this.accounts = new ArrayList<>();		
	}
	
	public Customer(String firstName, String lastName, String phoneNumber, String username, String password,
			int customerID) {
		super(firstName, lastName, phoneNumber, username, password);
		this.customerID = customerID;
		this.accounts = new ArrayList<>();		
	}
	
	public Customer(String firstName, String lastName, String phoneNumber, String username, String password,
			int customerID, ArrayList<Account> accounts) {
		super(firstName, lastName, phoneNumber, username, password);
		this.customerID = customerID;
		this.accounts = accounts;
		
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	public boolean addAccount(Account a) {	
			return accounts.add(a);
	}
	
	public boolean removeAccount(Account a) {
		if(accounts.contains(a)) {
			return accounts.remove(a);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(accounts, customerID);
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
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		return Objects.equals(accounts, other.accounts) && customerID == other.customerID;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", First Name=" + getFirstName()
				+ ", Last Name=" + getLastName() + ", Phone Number=" + getPhoneNumber() + "]";
	}

}
