package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Department {
	
	public static final String MARKER= "market";    //�г���    
	public static final String PRODUCTION = "production";     //������
	public static final String DEVAELOPMENT=     "development";    //�з���
	public static final String MANAGERMENT = "management";     // ����
	public static final String DIRECTOR = "director";     // ���»�
	
	private Integer id;
	private String department;
	private String explain;
	private Set<Staff> staffs = new HashSet<Staff>();
	private Set<Account> accounts = new HashSet<Account>();
	
	
	public Department(){
		
	}
	public Department(String d, String e) {
		this.department = d;
		this.explain = e;
				
	}
	
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
}
