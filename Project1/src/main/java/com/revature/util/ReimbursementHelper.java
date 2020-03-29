package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementHelper {
	
	public static ObjectMapper om = new ObjectMapper();
	public static ReimbursementService rs = new ReimbursementService();
	
	public static void handleGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		final String[] URI = req.getRequestURI().split("/");
		
		if (URI.length >= 3) {
			if (URI[2].equals("reimbursement") ) {
				res.getWriter().println(om.writeValueAsString(rs.findAll()));
			}
			else if (URI[2].equals("getreimbursement")) {
				res.getWriter().println(om.writeValueAsString(rs.getReimb(Integer.parseInt(URI[3]))));
			}
			else if (URI[2].equals("employeereimbursement")) {
				res.getWriter().println(om.writeValueAsString(rs.reimbOfEmployee(Integer.parseInt(URI[3]))));
			}
		} else {
			res.sendError(404, "path not found!");
		}
	}
	
	public static void handlePost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		final String[] URI = req.getRequestURI().split("/");
		
		if (URI.length >= 3) {
			if (URI[2].equals("updatereimbursement") ) {
				Reimbursement reimbursement = om.readValue(req.getReader(), Reimbursement.class);
				rs.update(reimbursement);
			}
			else if (URI[2].equals("insertreimbursement")) {
				Reimbursement reimbursement = om.readValue(req.getReader(), Reimbursement.class);
				rs.insert(reimbursement);
			}
		} else {
			res.sendError(404, "path not found!");
		}
	}
}
