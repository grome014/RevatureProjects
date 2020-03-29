package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {

	private int reimb_id;
	private double reimb_amount;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	private String reimb_description;
	private Blob reimb_receipt;
	private Employee reimb_author;
	private Employee reimb_resolver;
	private Status reimb_status;
	private Type reimb_type;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimb_id, double reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, Blob reimb_receipt, Employee reimb_author, Employee reimb_resolver,
			Status reimb_status, Type reimb_type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}
	
	public Reimbursement(int reimb_id, double reimb_amount, Timestamp reimb_submitted,
			String reimb_description, Employee reimb_author, Status reimb_status, Type reimb_type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_description = reimb_description;
		this.reimb_author = reimb_author;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public double getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public Blob getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(Blob reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public Employee getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(Employee reimb_author) {
		this.reimb_author = reimb_author;
	}

	public Employee getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(Employee reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public Status getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(Status reimb_status) {
		this.reimb_status = reimb_status;
	}

	public Type getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(Type reimb_type) {
		this.reimb_type = reimb_type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimb_amount, reimb_author, reimb_description, reimb_id, reimb_receipt, reimb_resolved,
				reimb_resolver, reimb_status, reimb_submitted, reimb_type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Reimbursement)) {
			return false;
		}
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(reimb_amount) == Double.doubleToLongBits(other.reimb_amount)
				&& Objects.equals(reimb_author, other.reimb_author)
				&& Objects.equals(reimb_description, other.reimb_description) && reimb_id == other.reimb_id
				&& Objects.equals(reimb_receipt, other.reimb_receipt)
				&& Objects.equals(reimb_resolved, other.reimb_resolved)
				&& Objects.equals(reimb_resolver, other.reimb_resolver) && reimb_status == other.reimb_status
				&& Objects.equals(reimb_submitted, other.reimb_submitted) && reimb_type == other.reimb_type;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_author=" + reimb_author + ", reimb_resolver="
				+ reimb_resolver + ", reimb_status=" + reimb_status + ", reimb_type=" + reimb_type + "]";
	}
	
	
	
	
}
