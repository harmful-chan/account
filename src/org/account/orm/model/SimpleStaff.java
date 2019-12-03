package org.account.orm.model;

public class SimpleStaff {
	private String number;
	private String department;
	private String role;
	private int[] permissions;
	
	public SimpleStaff() {
		
	}
	
	public SimpleStaff(String number, String department, String role, int[] permissions) {
		super();
		this.number = number;
		this.department = department;
		this.role = role;
		this.permissions = permissions;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int[] getPermissions() {
		return permissions;
	}
	public void setPermissions(int[] permissions) {
		this.permissions = permissions;
	}
	
	
}
