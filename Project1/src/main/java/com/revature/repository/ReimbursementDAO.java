package com.revature.repository;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ReimbursementDAO {

	public List<Reimbursement> findAll();
	public boolean insert(Reimbursement r);
	public boolean update(Reimbursement r);
	public List<Reimbursement> reimbOfEmployee(int empId);
	public Reimbursement getReimb(int id);
}
