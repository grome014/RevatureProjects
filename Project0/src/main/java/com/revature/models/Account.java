package com.revature.models;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
	
	private int accountID;
	private double balance;
	private Status status;
	private ArrayList<Customer> accountHolders;
	
	public Account() {
		super();
	}
	
	public Account(Customer accountHolder) {
		this.accountHolders = new ArrayList<>();
		this.balance = 0;
		this.status = Status.PENDING;
		this.addAccountHolder(accountHolder);
	}
	
	public Account(int accountID, double balance, Status status) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.status = status;
		this.accountHolders = new ArrayList<>();
	}

	public Account(int accountID, double balance, Status status, ArrayList<Customer> accountHolders) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.status = status;
		this.accountHolders = accountHolders;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<Customer> getaccountHolders() {
		return accountHolders;
	}

	public void setAccountHolders(ArrayList<Customer> accountHolders) {
		this.accountHolders = accountHolders;
	}
	
	public boolean addAccountHolder(Customer c) {
		if(!accountHolders.contains(c)) {
			return accountHolders.add(c);
		}
		
		return false;
	}
	
	public boolean removeAccountHolder(Customer c) {
		if(accountHolders.contains(c)) {
			return accountHolders.remove(c);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountHolders, accountID, balance, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return Objects.equals(accountHolders, other.accountHolders) && accountID == other.accountID
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && status == other.status;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", status=" + status + "]";
	}
	
	
	
}
