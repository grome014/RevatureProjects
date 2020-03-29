package com.revature.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementServiceTest {

private ReimbursementService reimbursementService = null;
private Reimbursement reimb = null;
	
	@Before
	public void setUp() throws Exception {
		reimbursementService = new ReimbursementService();
		reimb = reimbursementService.getReimb(1);
	}
	
	@Test
	public void testfindAll() {
		assertNotNull(reimbursementService.findAll());
	}
	
	@Test
	public void testGetReimb() {
		assertNotNull(reimbursementService.getReimb(1));
	}
	
	@Test
	public void testInsert() {
		assertTrue(reimbursementService.insert(reimb));
	}
	
	@Test
	public void testUpdate() {
		assertTrue(reimbursementService.update(reimb));
	}
	
	@Test
	public void testReimbOfEmployee() {
		assertNotNull(reimbursementService.reimbOfEmployee(1));
	}
	
}
