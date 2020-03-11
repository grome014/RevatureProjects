package com.revature.models;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu {

	public String customerMenuOptions(Customer customer, Scanner sc) {
		String option = "";
		
		System.out.println("\nWelcome " + customer.getFirstName() + ".");
		System.out.println("Please choose one of the following options:");
		System.out.println("\t1. Apply to open an account"
				+ "\n\t2. Apply for a joint account"
				+ "\n\t3. Account options"
				+ "\n\t4. Log out");
		
		option = sc.nextLine();
		
		return option;
	}
	
	public Account applyForAccount(Customer customer) {
		System.out.println("Thank you for applying for an account with us. Your account is under review "
				+ "waiting for approval.");
		Account account = new Account(customer);
		
		return account;
	}
	
	//this method will get a customer object and return the account to be operated on
	public Account accountOptions(Customer customer, Scanner sc) {
		String option = "";
		Account account = null;
		
		if(customer.getAccounts() == null) {
			System.out.println("You do not have any open accounts at the moment.");
			return account;
		}
		else if (!hasApprovedAccount(customer)){
			System.out.println("You do not have any approved accounts at the moment.");
			return account;
		}
		else {
			for(Account a : customer.getAccounts()) {
				if (a.getStatus() == Status.APPROVED) {
					System.out.println(a);
				}
			}
			System.out.println("Please select an account by entering the account ID: ");
			option = sc.nextLine();
			
			while (!verifyAccountID(customer, option)) {
				System.out.println("Please enter a valid ID!");
				option = sc.nextLine();
			}
			
			
			account = selectAccount(customer, option);
		}
		
		return account;
	}
	
	public boolean verifyAccountID(Customer customer, String option) {
		for(Account a : customer.getAccounts()) {
			if(a.getAccountID() == Integer.parseInt(option)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasApprovedAccount (Customer customer) {
		
		for(Account a : customer.getAccounts()) {
			if (a.getStatus() == Status.APPROVED) {
				return true;
			}
		}
		return false;
	}
	
	public Account selectAccount(Customer customer, String option) {
		for(Account a : customer.getAccounts()) {
			if (a.getAccountID() == Integer.parseInt(option)) {
				return a;
			}
		}
		
		return null;
	}
	
	public Account deposit(Account account, Scanner sc) {
		double deposit;
		
		System.out.println("Please enter the amount that you want to deposit: ");
		deposit = Double.parseDouble(sc.nextLine());
		account.setBalance(account.getBalance() + deposit);
		
		return account;
		
	}
	
	public Account withdraw(Account account, Scanner sc) {
		double amount;
		
		System.out.println("Please enter the amount that you want to withdraw: ");
		amount = Double.parseDouble(sc.nextLine());
		while (amount > account.getBalance()) {
			System.out.println("Invalid amount! The amount you are withdrawing must be greater "
					+ "than your account balance.");
			System.out.println("Account balance: " + account.getBalance());
			
			System.out.println("Please enter the amount that you want to withdraw: ");
			amount = Double.parseDouble(sc.nextLine());
			
		}
		account.setBalance(account.getBalance() - amount);
		
		return account;
	}
	
	public void transfer(Account account, Scanner sc) {
		
		
	}
	
	//list of account options
	public String accountTransactions(Scanner sc, Account account) {
		String option = "";
		
		System.out.println(account);
		System.out.println("What would you like to do today?");
		System.out.println("\t1. Deposit"
				+ "\n\t2. Withdraw"
				+ "\n\t3. Transfer"
				+ "\n\t4. Previous Menu");
		
		option = sc.nextLine();
		
		return option;
		
	}
	
	//There are two methods to complete the transfer function:
	//getAccountID which gets the account id of the account that you want to transfer
	//and getTransferAmount which gets the amount of the transfer
	public int getAccountID(ArrayList<Account> accountList, Scanner sc) {
		int accountID;
		boolean isIDValid = false;
		
		System.out.println("Please enter the account ID of the account that you want to transfer to: ");
		accountID = Integer.parseInt(sc.nextLine());
		
		for(Account a : accountList) {
			if (a.getAccountID() == accountID) {
				isIDValid = true;
			}
		}
		
		while (!isIDValid) {
			System.out.println("Please enter a valid account ID: ");
			accountID = Integer.parseInt(sc.nextLine());
			for(Account a : accountList) {
				if (a.getAccountID() == accountID) {
					isIDValid = true;
				}
			}
		}
		
		return accountID;

	}
	
	public double getTransferAmount(Account account, Scanner sc) {
		double amount;
		
		System.out.println("Please enter the amount that you want to transfer: ");
		amount = Double.parseDouble(sc.nextLine());
		
		while (amount > account.getBalance()) {
			System.out.println("Invalid amount! The amount you are withdrawing must be greater"
					+ "than your account balance.");
			System.out.println("Account balance: " + account.getBalance());
			
			System.out.println("Please enter the amount that you want to transfer: ");
			amount = Double.parseDouble(sc.nextLine());
			
		}
		
		return amount;
		
	}
}
