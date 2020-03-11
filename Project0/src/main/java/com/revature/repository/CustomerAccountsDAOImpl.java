package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.util.ConnectionUtil;

public class CustomerAccountsDAOImpl implements CustomerAccountsDAO {

	@Override
	public boolean insert(int customerID, int accountID) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO customer_accounts (customer_id, account_id)"
					+ "VALUES (?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, customerID);
			stmt.setInt(2, accountID);
			
			return stmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
