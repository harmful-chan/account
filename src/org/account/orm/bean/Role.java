package org.account.orm.bean;

import java.util.HashSet;
import java.util.Set;

public class Role {
	public static final String NORMAL  = "normal";   //普通员工    
	public static final String GOVERNOR = "governor";     //部门主管
	public static final String ADMINISTRATOR = "administrator";    //后台管理员
	public static final String ROOT = "root";    //最高管理员
	
	public static final int NORMAL_ID  = 1;    
	public static final int GOVERNOR_ID = 2;
	public static final int ADMINISTRATOR_ID = 3;
	public static final int ROOT_ID = 4;
	
	private Integer id;
	private String name;
	private String explain;
	private Set<Staff> staffs = new HashSet<Staff>();
	private Set<Account> accounts = new HashSet<Account>();
	private Set<Permission> permissions = new HashSet<Permission>();
    
	public Role(){
		
	}
	public Role(String n, String e) {
		this.name = n;
		this.explain = e;
	}
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", explain=" + explain + "]";
	}
    
    
}
