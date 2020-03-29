package com.revature.util;

import java.security.MessageDigest;

public class Crypto {
	
	private MessageDigest md;
	
	public String encryptPassword(String password) {
		String encryptedPassword = "";
		byte[] plainText = password.getBytes(); //Encodes the string into a sequence of bytes
		
		try {
			md = MessageDigest.getInstance("SHA-1"); //Returns a MessageDigest object that implements
			//the specified digest algorithm
			
			md.reset(); //Resets the digest for further use
			md.update(plainText); //Updates the digest using the specified byte
			byte[] encodedPassword = md.digest(); //Completes the hash computation by performing
			//final operations such as padding
			
			//Convert the byte array into HexString format
			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < encodedPassword.length; i++) {
				hexString.append(Integer.toHexString(0xFF & encodedPassword[i]));
			}
			
			encryptedPassword = hexString.toString();
			
			return encryptedPassword;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedPassword;
	}
	
	public boolean verifyPassword(String encryptedPassword, String password) {
		if (encryptedPassword.equals(encryptPassword(password))) {
			return true;
		}
		return false;
	}

}
