package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.*;
import com.revature.services.*;

public class Driver {
	
	private static ArrayList<Customer> customerList;
	private static ArrayList<Account> accountList;
	private static ArrayList<Employee> employeeList;
	private static Customer customer;
	private static Account account;
	private static Employee employee;
	private static AccountService accountService;
	private static CustomerService customerService;
	private static EmployeeService employeeService;
	private static CustomerAccountsService customerAccountsService;
	
	private static Logger log = Logger.getLogger(Driver.class);

	public static void main(String[] args) {
		
		log.info("Application start");
		MainMenu mainMenu = new MainMenu();
		customerService = new CustomerService();
		accountService = new AccountService();
		employeeService = new EmployeeService();
		customerAccountsService = new CustomerAccountsService();
		CustomerMenu customerMenu = new CustomerMenu();
		EmployeeMenu employeeMenu = new EmployeeMenu();
		AdminMenu adminMenu = new AdminMenu();
		
		String option = "";
		customerList = customerService.findAll();
		accountList = accountService.findAll();
		employeeList = employeeService.findAll();
		
		option = mainMenu.runBankApp();
		
		while(!option.equals("4")) {
		
			switch(option) {
			case "1":
				customer = mainMenu.newCustomerCreation(customerList);
				customer.setCustomerID(customerService.insert(customer));
				
				customerList.add(customer);
				break;
			case "2":
				customer = mainMenu.existingCustomer(customerList);
				if (customer != null) {
					try {
						runCustomerMenu(mainMenu.getSc(), customerMenu);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case "3":
				employee = mainMenu.bankEmployee(employeeList);
				if(employee != null) {
					if (employee.getRole().toString().toUpperCase().equals("ADMIN")) {
						runAdminMenuOptions(adminMenu, mainMenu.getSc(), customerMenu);
					} else {
						runEmployeeMenu(employeeMenu, mainMenu.getSc());
					}
				}
			}
			
			option = mainMenu.runBankApp();
			
		}
		
		System.out.println("Good bye!");
		mainMenu.closeSc();
		log.info("Application end");
	}
	
	
//	1. Apply to open an account"
//	2. Apply for a joint account"
//	3. Account options"
//	4. Log out
	public static void runCustomerMenu(Scanner sc, CustomerMenu customerMenu) throws InterruptedException {
		String option = customerMenu.customerMenuOptions(customer, sc);
		
		while(!option.equals("4")) {
			switch(option) {
			case "1":
				account = customerMenu.applyForAccount(customer);
				account.setAccountID(accountService.insert(account));
				accountList.add(account);
				customerAccountsService.insert(customer.getCustomerID(), account.getAccountID());
				break;
			case "2":
				System.out.println("Feature under construction!\n");
				break;
			case "3":
				account = customerMenu.accountOptions(customer, sc);
				if (account != null) {
					runAccountTransactions(customerMenu, sc);
				}
				break;
			}
			
			option = customerMenu.customerMenuOptions(customer, sc);
			
		}
	}

//	 	1. Deposit"
//		2. Withdraw"
//		3. Transfer"
//		4. Previous Menu"
	public static void runAccountTransactions(CustomerMenu customerMenu, Scanner sc) {
		String option = customerMenu.accountTransactions(sc, account);
		
		while(!option.equals("4")) {
			switch(option) {
			case "1":
				log.info("Starting deposit");
				account = customerMenu.deposit(account, sc);
				if (!accountService.deposit(account)) {
					System.out.println("Deposit successful!");
					log.info("Deposit successful");
				}
				
				break;
			case "2":
				log.info("Starting withdraw");
				account = customerMenu.withdraw(account, sc);
				if (!accountService.withdraw(account)) {
					System.out.println("Withdrawal successful!");
					log.info("Withdrawal successful");
				}
				
				break;
			case "3":
				log.info("Starting transfer");
				int accountID = customerMenu.getAccountID(accountList, sc);
				double amount = customerMenu.getTransferAmount(account, sc);
				
				if(!accountService.transfer(account, accountID, amount)) {
					System.out.println("Transfer Successful!");
					log.info("Transfer successful");
				}
				
				accountList = accountService.findAll();
				customerList = customerService.findAll();
				for (Account a : accountList) {
					if (a.getAccountID() == account.getAccountID()) {
						account = a;
					}
				}
				break;
			}
			
			option = customerMenu.accountTransactions(sc, account);
			
		}
	}
	
//		1. View customer information"
//		2. Approve/deny accounts"
//		3. Log out"
	public static void runEmployeeMenu(EmployeeMenu employeeMenu, Scanner sc) {
		String option = employeeMenu.employeeMenuOptions(employee, sc);
		
		while(!option.equals("3")) {
			switch(option) {
			case "1":
				for(Customer c : customerList) {
					System.out.println(c);
					for(Account a : c.getAccounts()) {
						System.out.println("\t" + a);
					}
				}
				break;
			case "2":
				account = employeeMenu.selectAccount(accountList, sc);
				if (account != null) {
					account = employeeMenu.approveAccounts(account, sc);
					if (!accountService.changeStatus(account)) {
						//System.out.println("Status updated!\n");
						customerList = customerService.findAll();
					}
				}
				
				break;

			}
			
			option = employeeMenu.employeeMenuOptions(employee, sc);
			
		}
		
	}
	
//		1. View customer information"
//		2. Approve/deny accounts"
//		3. Account options"
//		4. Cancel account"
//		5. Log out"
	public static void runAdminMenuOptions(AdminMenu adminMenu, Scanner sc, CustomerMenu customerMenu) {
		
		String option = adminMenu.adminMenuOptions(employee, sc);
		
		while(!option.equals("5")) {
			switch(option) {
			case "1":
				for(Customer c : customerList) {
					System.out.println(c);
					for(Account a : c.getAccounts()) {
						System.out.println("\t" + a);
					}
				}
				break;
			case "2":
				account = adminMenu.selectAccount(accountList, sc);
				if (account != null) {
					account = adminMenu.approveAccounts(account, sc);
					if (!accountService.changeStatus(account)) {
						//System.out.println("Status updated!\n");
						customerList = customerService.findAll();
						accountList = accountService.findAll();
					}
				}
				
				break;
			case "3":
				account = adminMenu.accountOptions(accountList, sc);
				runAccountTransactions(customerMenu, sc);
				break;
			case "4":
				account = adminMenu.chooseAccountDelete(accountList, sc);
				if (account != null) {
					account = adminMenu.deleteAccount(account, sc);
					if (account != null) {
						accountService.delete(account);
						System.out.println("Account deleted!");
						
						customerList = customerService.findAll();
					}
				}
				break;

			}
			
			option = adminMenu.adminMenuOptions(employee, sc);		
		}
	}
}
