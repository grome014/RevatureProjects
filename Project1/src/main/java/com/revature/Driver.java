package com.revature;

import com.revature.util.Crypto;

public class Driver {

	public static void main(String[] args) {

		Crypto encrypt = new Crypto();
		String password;
		String encryptedPassword;
		
		
		password = "pass1";
		encryptedPassword = encrypt.encryptPassword(password);
		
		System.out.println(password);
		System.out.println(encryptedPassword);
		
		System.out.println(encrypt.verifyPassword(encryptedPassword, password));
	}

}
