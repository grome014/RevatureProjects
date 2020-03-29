package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int role_id = rs.getInt("user_role_id");
				Role role;
				switch(role_id) {
					case 1:
						role = Role.EMPLOYEE;
						break;
					case 2:
						role = Role.MANAGER;
						break;
					default:
						role = Role.EMPLOYEE;
						break;
				}
				
				int id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				
				Employee employee = new Employee(id, username, password, firstName, lastName, email, role);
				
				list.add(employee);
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public Employee findById(int empId) {
		
		Employee employee = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int role_id = rs.getInt("user_role_id");
				Role role;
				switch(role_id) {
					case 1:
						role = Role.EMPLOYEE;
						break;
					case 2:
						role = Role.MANAGER;
						break;
					default:
						role = Role.EMPLOYEE;
						break;
				}
				
				int id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				
				employee = new Employee(id, username, password, firstName, lastName, email, role);
				
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return employee;
	}
	

}
