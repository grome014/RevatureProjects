package com.revature.servlets;

import java.io.IOException;import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.EmployeeHelper;
import com.revature.util.ReimbursementHelper;

public class FrontController extends HttpServlet {
	
	private static Logger log = LogManager.getLogger(FrontController.class);

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		final String[] URI = req.getRequestURI().split("/");
		
		if(URI.length >= 3) {
			switch(URI[2]) {
				case "reimbursement":
					log.info("Attempting to grab all reimbursements.");
					ReimbursementHelper.handleGet(req, res);
					log.info("Got all reimbursements.");
					break;
				case "getreimbursement":
					log.info("Attempting to grab a reimbursements.");
					ReimbursementHelper.handleGet(req, res);
					log.info("Got the reimbursement.");
					break;
				case "employeereimbursement":
					log.info("Attempting to get reimbursements of employee.");
					ReimbursementHelper.handleGet(req, res);
					log.info("Got the reimbursements.");
					break;
				case "getemployee":
					log.info("Attempting to get employee.");
					EmployeeHelper.handleGet(req, res);
					log.info("Got employee.");
					break;
				default:
					res.getWriter().write("404 Error, page not found!");
			}
		} else {
			res.sendError(404, "path not found!");
		}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		final String[] URI = req.getRequestURI().split("/");
		
		if(URI.length >= 3) {
			switch(URI[2]) {
				case "updatereimbursement":
					log.info("Attempting to update reimbursement.");
					ReimbursementHelper.handlePost(req, res);
					log.info("Updated reimbursement.");
					break;
				case "insertreimbursement":
					log.info("Attempting to insert reimbursement.");
					ReimbursementHelper.handlePost(req, res);
					log.info("Inserted the reimbursement.");
					break;
				case "login":
					log.info("Attempting to login");
					EmployeeHelper.handlePost(req, res);
					log.info("Login successful");
					break;
				default:
					res.getWriter().write("404 Error, page not found!");
			}
		} else {
			res.sendError(404, "path not found!");
		}
		
	}
	

}
