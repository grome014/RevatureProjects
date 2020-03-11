package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Status;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public int insert(Account a) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "{ call insert_into_accounts(?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.registerOutParameter(1, Types.INTEGER);
			
			
			if(!stmt.execute()) {
				return (Integer) stmt.getObject(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean transfer(Account source, int targetID, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "{ call transfer_funds(?, ?, ?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, source.getAccountID());
			stmt.setInt(2, targetID);
			stmt.setDouble(3, amount);
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Account> findAll() {
		ArrayList<Account> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				Status status = Status.valueOf(rs.getString("status").toUpperCase());
				
				Account a = new Account(id, balance, status);
				
				ArrayList<Customer> accountHolders = new CustomerDAOImpl().findByAccount(a);
				a.setAccountHolders(accountHolders);
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean deposit(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE accounts "
					+ "SET balance = ? "
					+ "WHERE account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getAccountID());
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean withdraw(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE accounts "
					+ "SET balance = ? "
					+ "WHERE account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getAccountID());
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changeStatus(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE accounts "
					+ "SET status = ? "
					+ "WHERE account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getStatus().toString());
			stmt.setInt(2, a.getAccountID());
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM accounts "
					+ "WHERE account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, a.getAccountID());
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Account> findByOwner(Customer customer) {
		ArrayList<Account> list = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * "
					+ "FROM accounts "
					+ "INNER JOIN customer_accounts USING(account_id) "
					+ "INNER JOIN bank_customer USING (customer_id) "
					+ "WHERE customer_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, customer.getCustomerID());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				int account_id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				Status status = Status.valueOf(rs.getString("status").toUpperCase());
				
				Account a = new Account(account_id, balance, status);
				a.addAccountHolder(customer);
				list.add(a);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
