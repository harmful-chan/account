package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

public class Account {
	private Integer id;
	private String number;
	private String password;
	private String salf;
	private String explain;
	private Set<Staff> staffs = new HashSet<Staff>();
	private Set<Department> departments = new HashSet<Department>();
	private Set<Role> roles = new HashSet<Role>();
    
    public Account(String number, String password, String salf, String explain) {
    	this.number = number;
    	this.password = password;
    	this.salf = salf;
    	this.explain = explain;
    }
    
    public Account() {
    	
    }
    
    
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	public Set<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalf() {
		return salf;
	}
	public void setSalf(String salf) {
		this.salf = salf;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", account=" + number + ", password=" + password + ", salf=" + salf + ", explain=" + explain+"]";
	}
    
    
}
