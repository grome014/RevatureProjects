package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Status;
import com.revature.util.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public int insert(Customer customer) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "{ call insert_into_bank_customer(?, ?, ?, ?, ?, ?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.registerOutParameter(1, Types.INTEGER);
			
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getLastName());
			stmt.setString(4, customer.getPhoneNumber());
			stmt.setString(5, customer.getUsername());
			stmt.setString(6, customer.getPassword());
			
			if(!stmt.execute()) {
				return (Integer) stmt.getObject(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ArrayList<Customer> findAll() {
		ArrayList<Customer> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_customer";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phoneNumber = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int customerID = rs.getInt("customer_id");
				
				Customer customer = new Customer(firstName, lastName, phoneNumber, username, password, customerID);
				
				ArrayList<Account> accounts = new AccountDAOImpl().findByOwner(customer);
				customer.setAccounts(accounts);
				
				list.add(customer);
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public ArrayList<Customer> findByAccount(Account account) {
		ArrayList<Customer> list = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * "
					+ "FROM accounts "
					+ "INNER JOIN customer_accounts USING(account_id) "
					+ "INNER JOIN bank_customer USING (customer_id) "
					+ "WHERE account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, account.getAccountID());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phoneNumber = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int customerID = rs.getInt("customer_id");
				
				Customer c = new Customer(firstName, lastName, phoneNumber, username, password, customerID);
				c.addAccount(account);
				list.add(c);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
