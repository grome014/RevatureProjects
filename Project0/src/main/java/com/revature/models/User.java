package com.revature.models;

import java.util.Objects;

public abstract class User {
	
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private String username;
		private String password;
		
		public User() {
			super();
		}

		public User(String firstName, String lastName, String phoneNumber, String username, String password) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.username = username;
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public int hashCode() {
			return Objects.hash(firstName, lastName, password, phoneNumber, username);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof User)) {
				return false;
			}
			User other = (User) obj;
			return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
					&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
					&& Objects.equals(username, other.username);
		}

		@Override
		public String toString() {
			return "User [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
					+ ", username=" + username + ", password=" + password + "]";
		}
		
}
