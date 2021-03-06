CREATE TABLE bank_customer (
	customer_id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	first_name VARCHAR2(55) NOT NULL,
	last_name VARCHAR2(55) NOT NULL,
	phone_number VARCHAR2(20) UNIQUE NOT NULL,
	username VARCHAR2(55) UNIQUE NOT NULL,
	password VARCHAR2(55) NOT NULL
);

CREATE TABLE bank_employees (
	employee_id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	first_name VARCHAR2(55) NOT NULL,
	last_name VARCHAR2(55) NOT NULL,
	phone_number VARCHAR2(20) UNIQUE NOT NULL,
	username VARCHAR2(55) UNIQUE NOT NULL,
	password VARCHAR2(55) NOT NULL,
	ROLE VARCHAR2(20) NOT NULL
);

CREATE TABLE accounts (
	account_id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	balance NUMBER(38, 2) DEFAULT 0,
	status VARCHAR2(20) DEFAULT 'Pending' NOT NULL
);

CREATE TABLE customer_accounts (
	customer_id NUMBER REFERENCES bank_customer (customer_id),
	account_id NUMBER REFERENCES accounts (account_id),
	
	PRIMARY KEY (customer_id, account_id)
);

CREATE OR REPLACE PROCEDURE insert_into_bank_customer(
	cust_id OUT NUMBER, cust_first IN VARCHAR2, cust_last IN VARCHAR2,
	cust_phone IN VARCHAR2, cust_username IN VARCHAR2, cust_password IN VARCHAR2
)
IS
BEGIN
	INSERT INTO bank_customer (FIRST_NAME, LAST_NAME, phone_number, username, password)
		VALUES (cust_first, cust_last, cust_phone, cust_username, cust_password)
		RETURNING customer_id
		INTO cust_id;
END;

DROP TABLE ACCOUNTS CASCADE CONSTRAINTS;
DROP TABLE BANK_CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE BANK_EMPLOYEES CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER_ACCOUNTS CASCADE CONSTRAINTS;