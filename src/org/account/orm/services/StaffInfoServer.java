package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.impl.AccountImpl;
import org.account.orm.impl.StaffImpl;
import org.account.orm.model.*;

public class StaffInfoServer {
	
	StaffImpl si = null;
	AccountImpl ai = null;
	public StaffInfoServer() {
		this.si = new StaffImpl();
		this.ai = new AccountImpl(); 
	}
	
	
	
	public Staff getStaff(String number) {
		return this.si.findByNumber(number);
	}
	
	public void alterStaffByNumber(Staff staff) {
		this.si.updateByNumber(staff);
	}
	
	
	public Department getDepartment(String number){
		Staff staff = this.si.findByNumber(number);
		return staff.getDepartment();
	}
	
	public Role getRole(String number) {
		Staff staff = this.si.findByNumber(number);
		return staff.getRole(); 
	}
	
	public Account getAccount(String number) {
		Staff staff = this.si.findByNumber(number);
		return staff.getAccount();
	}
	
	
	public List<Permission> getPermissions(String number){
		return (List<Permission>)this.si.findPrimissionsByNumber(number);
	}
	
	public List<Node> getNodes(String number){
		return (List<Node>)this.si.findNodesByNumber(number);
	}
	
	
	public List<Staff> getHold(){
		return this.si.findHold();
	}
	
	public void addHold(String number, Account account) {
		Staff staff = this.si.findByNumber(number);
		Account staffAccount = this.ai.findByNumber(account.getNumber());
		if(staffAccount != null) {
			staff.setAccount(staffAccount);
		}else {
			staff.setAccount(account);
			this.ai.add(account);
		}
		this.si.modify(staff);
	}
	
}
