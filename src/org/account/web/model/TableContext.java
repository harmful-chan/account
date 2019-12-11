package org.account.web.model;

public class TableContext {
	private String operator;
	public String accountNumber;
	public String deeppwd;
	public String ownerDepartment;
	public String ownerRole;
	public String ownerNumber;
	public boolean valid;
	public String explain;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDeeppwd() {
		return deeppwd;
	}
	public void setDeeppwd(String deeppwd) {
		this.deeppwd = deeppwd;
	}
	public String getOwnerDepartment() {
		return ownerDepartment;
	}
	public void setOwnerDepartment(String ownerDepartment) {
		this.ownerDepartment = ownerDepartment;
	}
	public String getOwnerRole() {
		return ownerRole;
	}
	public void setOwnerRole(String ownerRole) {
		this.ownerRole = ownerRole;
	}
	public String getOwnerNumber() {
		return ownerNumber;
	}
	public void setOwnerNumber(String ownerNumber) {
		this.ownerNumber = ownerNumber;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	
	
}
