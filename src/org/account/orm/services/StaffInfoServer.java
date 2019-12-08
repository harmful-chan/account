package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.impl.StaffImpl;
import org.account.orm.model.*;

public class StaffInfoServer {
	
	StaffImpl si = null;
	public StaffInfoServer() {
		this.si = new StaffImpl();
	}
	
	public Staff getStaff(String number) {
		return this.si.findByNumber(number);
	}
	public Department getDepartment(String number){
		Staff staff = this.getStaff(number);
		return staff.getDepartment();
	}
	public Role getRole(String number) {
		return (Role)this.si.findRoleByNumber(number);
		 
	}
	public List<Permission> getPermissions(String number){
		return (List<Permission>)this.si.findPrimissionsByNumber(number);
	}
	public List<Node> getNodes(String number){
		return (List<Node>)this.si.findNodesByNumber(number);
	}

}
