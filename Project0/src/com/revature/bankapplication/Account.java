package com.revature.bankapplication;

public class Account {
	private Customer accountHolder;
	private int accountNumber;
	private int balance;
	private static int counter = 0;
	
	public Account(Customer accountHolder, int balance) {
		super();
		this.accountHolder = accountHolder;
		this.balance = balance;
		this.accountNumber = counter++;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}

