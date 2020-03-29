package com.revature.repository;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public List<Reimbursement> findAll() {
		
		List<Reimbursement> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getDouble("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				Blob reimb_receipt = rs.getBlob("reimb_receipt");
				Employee reimb_author = getEmployee(rs.getInt("reimb_author"));
				Employee reimb_resolver = getEmployee(rs.getInt("reimb_resolver"));
				Status reimb_status = getStatus(rs.getInt("reimb_status_id"));
				Type reimb_type = getType(rs.getInt("reimb_type_id"));
				 
				
				
				Reimbursement reimbursement = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, 
						reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver,
						reimb_status, reimb_type);
				
				list.add(reimbursement);
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public boolean insert(Reimbursement r) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, "
					+ "reimb_description, reimb_author, reimb_status_id, reimb_type_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//stmt.setInt(1, r.getReimb_id());
			stmt.setDouble(1, r.getReimb_amount());
			stmt.setTimestamp(2, r.getReimb_submitted());
			stmt.setString(3, r.getReimb_description());
			stmt.setInt(4, r.getReimb_author().getId());
			stmt.setInt(5, setStatus(r.getReimb_status()));
			stmt.setInt(6, setType(r.getReimb_type()));
			
			stmt.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Reimbursement r) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE ers_reimbursement "
					+ "SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? "
					+ "WHERE reimb_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setTimestamp(1, r.getReimb_resolved());
			//stmt.setInt(2, r.getReimb_resolver().getId());
			stmt.setInt(2, 2);
			stmt.setInt(3, setStatus(r.getReimb_status()));
			stmt.setInt(4, r.getReimb_id());
			
			stmt.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> reimbOfEmployee(int empId) {
		List<Reimbursement> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getDouble("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				Blob reimb_receipt = rs.getBlob("reimb_receipt");
				Employee reimb_author = getEmployee(rs.getInt("reimb_author"));
				Employee reimb_resolver = getEmployee(rs.getInt("reimb_resolver"));
				Status reimb_status = getStatus(rs.getInt("reimb_status_id"));
				Type reimb_type = getType(rs.getInt("reimb_type_id"));			
				
				Reimbursement reimbursement = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, 
						reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver,
						reimb_status, reimb_type);
				
				list.add(reimbursement);
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}
	
	public Status getStatus(int status)
	{
		if (status == 1) {
			return Status.PENDING;
		} else if (status == 2) {
			return Status.APPROVED;
		}
		return Status.DENIED;
	}
	
	public Type getType(int type) {
		if(type == 1) {
			return Type.LODGING;
		} else if (type == 2) {
			return Type.TRAVEL;
		} else if (type == 3) {
			return Type.FOOD;
		}
		return Type.OTHER;
	}
	
	public Employee getEmployee(int empId) {
		EmployeeService service = new EmployeeService();
		Employee emp = service.findById(empId);
		return emp;
	}
	
	public int setStatus(Status status) {
		if (status.equals(Status.PENDING)) {
			return 1;
		} else if (status.equals(Status.APPROVED)){
			return 2;
		}	
		return 3;
	}
	
	public int setType(Type type) {
		if (type.equals(Type.LODGING)) {
			return 1;
		} else if (type.equals(Type.TRAVEL)) {
			return 2;
		} else if (type.equals(Type.FOOD)) {
			return 3;
		}
		
		return 4;
	}

	@Override
	public Reimbursement getReimb(int id) {
		Reimbursement reimbursement = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getDouble("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				Blob reimb_receipt = rs.getBlob("reimb_receipt");
				Employee reimb_author = getEmployee(rs.getInt("reimb_author"));
				Employee reimb_resolver = getEmployee(rs.getInt("reimb_resolver"));
				Status reimb_status = getStatus(rs.getInt("reimb_status_id"));
				Type reimb_type = getType(rs.getInt("reimb_type_id"));
				 
				
				
				reimbursement = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, 
						reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver,
						reimb_status, reimb_type);
				
			}
			
					
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return reimbursement;
	}

}
