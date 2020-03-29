package com.revature.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Login;
import com.revature.services.EmployeeService;


public class EmployeeHelper {
	
	public static ObjectMapper om = new ObjectMapper();
	public static EmployeeService es = new EmployeeService();
	public static Crypto crypto = new Crypto();
	
	public static void handleGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		final String[] URI = req.getRequestURI().split("/");
		
		if (URI.length >= 3) {
			if (URI[2].equals("getemployee")) {
				res.getWriter().println(om.writeValueAsString(es.findById(Integer.parseInt(URI[3]))));
			}
		} else {
			res.sendError(404, "path not found!");
		}
	}
	
	public static void handlePost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		final String[] URI = req.getRequestURI().split("/");
		
		if (URI.length >= 3) {
			if (URI[2].equals("login") ) {
				
				Employee employee = null;
				Login login = om.readValue(req.getReader(), Login.class);
				List<Employee> emps = es.findAll();
				login.setPassword(crypto.encryptPassword(login.getPassword()));
				
				for(Employee e : emps) {
					if(e.getUsername().equals(login.getUsername()) 
							&& e.getPassword().equals(login.getPassword())) {
						employee = e;
					}
				}
				
				res.getWriter().println(om.writeValueAsString(employee));
			}
		} else {
			res.sendError(404, "path not found!");
		}
	}

}
