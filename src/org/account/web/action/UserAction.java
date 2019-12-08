package org.account.web.action;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.SystemManagement;
import org.account.orm.model.*;
import org.account.web.viewmodel.Table;
import org.account.web.viewmodel.User;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction  extends ActionBase implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	@Override
	public User getModel() {
		return this.user;
	}
	
	public String getInfo() {
		User uvm = new User();
		Staff  staff = arbitrate.getStaff(user.getNumber());
		uvm.setNumber(arbitrate.getCurrentActiveStaff().getNumber());
		uvm.setAccount(arbitrate.getCurrentActiveStaff().getAccount());
		uvm.setRole(arbitrate.getCurrentActiveStaff().getRole());
		uvm.setDepartment(arbitrate.getCurrentActiveStaff().getDepartment());
		
		uvm.setEntryDate(staff.getEntryDate());
		uvm.setCountry(staff.getCountry());
		uvm.setProvince(staff.getProvince());
		uvm.setCity(staff.getCity());
		uvm.setZipCode(staff.getZipCode());
		uvm.setFirstName(staff.getFirstName());
		uvm.setLastName(staff.getLastName());
		
		String ps = "";
		for (int p : arbitrate.getCurrentActiveStaff().getPermissions()) {
			ps += p + ", ";
		}
		uvm.setPermissioms(ps);
		
		session.put("user_info", uvm);
		session.put("update_url", "http://localhost:8080/account/UserProfile/alterdInfo");
		return SUCCESS;
	}
	
	public String alterdInfo() {
	
		arbitrate.alterStaff(user.getNumber(), user.getFirstName(), user.getLastName(), user.getCity(), 
				user.getProvince(), user.getCountry(), user.getZipCode(), user.getEntryDate());
		return getInfo();
	}
}
