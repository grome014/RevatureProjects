package com.revature.models;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu extends EmployeeMenu {
	
	public String adminMenuOptions(Employee admin, Scanner sc) {
		String option = "";
		
		System.out.println("\nWelcome " + admin.getFirstName() + ".");
		System.out.println("Please choose one of the following options:");
		System.out.println("\t1. View customer information"
				+ "\n\t2. Approve/deny accounts"
				+ "\n\t3. Account options"
				+ "\n\t4. Cancel account"
				+ "\n\t5. Log out");
		
		option = sc.nextLine();
		
		return option;
		
	}
	
	public Account accountOptions(ArrayList<Account> accountList, Scanner sc) {
		String option = "";
		Account account = null;
		
		if(accountList == null) {
			System.out.println("There are no open accounts at the moment.");
		}
		else if (!hasApprovedAccount(accountList)){
			System.out.println("There are no approved accounts at the moment.");			
		}
		else {
			for(Account a : accountList) {
				if (a.getStatus() == Status.APPROVED) {
					System.out.println(a);
				}
			}
			System.out.println("Please select an account by entering the account ID: ");
			option = sc.nextLine();
			
			while (!verifyAccountID(accountList, option)) {
				System.out.println("Please enter a valid ID!");
				option = sc.nextLine();
			}
			
			
			account = selectAccount(accountList, option);
		}
		
		return account;
	}
	
	public boolean hasApprovedAccount(ArrayList<Account> accountList) {
		for(Account a : accountList) {
			if (a.getStatus() == Status.APPROVED) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyAccountID(ArrayList<Account> accountList, String option) {
		for(Account a : accountList) {
			if(a.getAccountID() == Integer.parseInt(option)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Account selectAccount(ArrayList<Account> accountList, String option) {
		for(Account a : accountList) {
			if (a.getAccountID() == Integer.parseInt(option)) {
				return a;
			}
		}
		
		return null;
	}
	
	
	public Account chooseAccountDelete(ArrayList<Account> accountList, Scanner sc) {
		String option = "";
		Account account = null;
		

		for (Account a : accountList) {
				System.out.println(a);
		}
		System.out.println("Please enter the account ID of the account to be processed: ");
		option = sc.nextLine();
		
		while (!accountDeleteValidation(accountList, Integer.parseInt(option))) {
			System.out.println("Please enter a valid account ID: ");
			option = sc.nextLine();
		}
		
		account = getAccount(accountList, Integer.parseInt(option));
		
		
		return account;
		
	}

	public boolean accountDeleteValidation(ArrayList<Account> accountList, int accountID) {
		for (Account a : accountList) {
			if (a.getAccountID() == accountID) {
				return true;
			}
		}
		return false;
	}
	
	public Account deleteAccount(Account account, Scanner sc) {
		String option = "";
		
		System.out.println(account);
		System.out.println("Select one of the following options: ");
		System.out.println("\t1. Delete"
				+ "\n\t2. Go back to previous menu");
		option = sc.nextLine();
		
		if(option.equals("1")) {
			return account;
		} else {
			return null;
		}
	}
}
