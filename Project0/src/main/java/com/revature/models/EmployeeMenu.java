package com.revature.models;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMenu {
	public String employeeMenuOptions(Employee employee, Scanner sc) {
		String option = "";
		
		System.out.println("\nWelcome " + employee.getFirstName() + ".");
		System.out.println("Please choose one of the following options:");
		System.out.println("\t1. View customer information"
				+ "\n\t2. Approve/decline accounts"
				+ "\n\t3. Log out");
		
		option = sc.nextLine();
		
		return option;
	}
	
	public Account selectAccount(ArrayList<Account> accountList, Scanner sc) {
		String option = "";
		Account account = null;
		
		if (hasAccountPending(accountList)) {
			for (Account a : accountList) {
				if (a.getStatus().equals(Status.PENDING)) {
					System.out.println(a);
				}
			}
			System.out.println("Please enter the account ID of the account to be processed: ");
			option = sc.nextLine();
			
			while (!accountInputValidation(accountList, Integer.parseInt(option))) {
				System.out.println("Please enter a valid account ID: ");
				option = sc.nextLine();
			}
			
			account = getAccount(accountList, Integer.parseInt(option));
		}
		else {
			System.out.println("There are no accounts pending at the moment.");
		}
		
		
		return account;
		
	}
	
	public boolean hasAccountPending(ArrayList<Account> accountList) {
		for (Account a : accountList) {
			if (a.getStatus().equals(Status.PENDING)) {
				return true;
			}
		}
		return false;
	}
	
	public Account getAccount(ArrayList<Account> accountList, int accountID) {
		for (Account a : accountList) {
			if (a.getAccountID() == accountID) {
				return a;
			}
		}
		return null;
	}
	
	public boolean accountInputValidation(ArrayList<Account> accountList, int accountID) {
		for (Account a : accountList) {
			if (a.getStatus().equals(Status.PENDING) && a.getAccountID() == accountID) {
				return true;
			}
		}
		return false;
	}
	
	public Account approveAccounts(Account account, Scanner sc) {
		String option = "";
		
		System.out.println(account);
		System.out.println("Select one of the following options: ");
		System.out.println("\t1. Approve"
				+ "\n\t2. Decline"
				+ "\n\t3. Go back to previous menu");
		
		option = sc.nextLine();
		
		if(option.equals("1")) {
			account.setStatus(Status.APPROVED);
			System.out.println("Status Updated!");
			return account;
		} else if (option.equals("2")) {
			account.setStatus(Status.DECLINED);
			System.out.println("Status Updated!");
			return account;
		}
		
		System.out.println("No changes were made to the account!");
		
		return account;
		
	}
}
