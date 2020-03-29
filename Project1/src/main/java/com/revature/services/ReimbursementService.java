package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.repository.ReimbursementDAO;
import com.revature.repository.ReimbursementDAOImpl;

public class ReimbursementService {

	ReimbursementDAO repository = null;

	public ReimbursementService() {
		super();
		this.repository = new ReimbursementDAOImpl();
	}

	public ReimbursementService(ReimbursementDAO repository) {
		super();
		this.repository = repository;
	}
	
	public List<Reimbursement> findAll() {
		return repository.findAll();
	}
	
	public boolean insert(Reimbursement r) {
		return repository.insert(r);
	}
	
	public boolean update(Reimbursement r) {
		return repository.update(r);
	}
	
	public List<Reimbursement> reimbOfEmployee(int empId) {
		return repository.reimbOfEmployee(empId);
	}
	
	public Reimbursement getReimb(int id) {
		return repository.getReimb(id);
	}
}
