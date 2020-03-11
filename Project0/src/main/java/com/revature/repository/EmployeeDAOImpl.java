package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public int insert(Employee employee) {
		return 0;
	}

	@Override
	public ArrayList<Employee> findAll() {
		
		ArrayList<Employee> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_employees";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phoneNumber = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int employeeID = rs.getInt("employee_id");
				Role role = Role.valueOf(rs.getString("role").toUpperCase());
				
				Employee employee = new Employee(firstName, lastName, phoneNumber, username, password, role, employeeID);
				
				list.add(employee);
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public Employee findById(int id) {
		return null;
	}

}
