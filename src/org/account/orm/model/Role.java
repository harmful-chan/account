package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private int id;
	private String role;
	private String explain;
	private Set<Staff> staffs = new HashSet<Staff>();
	private Set<Account> accounts = new HashSet<Account>();
	private Set<Permission> permissions = new HashSet<Permission>();
    
    
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
    
    
}
