package org.account.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.model.*;
import org.account.web.model.TableContext;
import org.account.web.model.UserContext;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction  extends ActionBase implements ModelDriven<UserContext> {
	private static final long serialVersionUID = 1L;

	private UserContext userRequest = new UserContext();
	@Override
	public UserContext getModel() {
		return this.userRequest;
	}
	
	public String getInfo() {
		
		Staff  staff = staffInfo.getStaff(active.getCurrent());
		Role  role = staffInfo.getRole(active.getCurrent());
		Department  department = staffInfo.getDepartment(active.getCurrent());
		Account  account = staffInfo.getAccount(active.getCurrent());
		List<Permission> permissions = staffInfo.getPermissions(active.getCurrent());
		
		UserContext uvm = new UserContext();
		uvm.setNumber(staff.getNumber());
		uvm.setAccountNumber(account.getNumber());
		uvm.setRole(role.getName());
		uvm.setDepartment(department.getName());
		uvm.setEntryDate(staff.getEntryDate());
		uvm.setCountry(staff.getCountry());
		uvm.setProvince(staff.getProvince());
		uvm.setCity(staff.getCity());
		uvm.setZipCode(staff.getZipCode());
		uvm.setFirstName(staff.getFirstName());
		uvm.setLastName(staff.getLastName());
		
		String pStr = "";
		
		for (Permission permission : permissions) {
			pStr += permission.getCode() + ".";
		}
		uvm.setPermissioms(pStr);
		
		session.put("user_info", uvm);
		session.put("user_update_url", "http://localhost:8080/account/UserProfile/alterdInfo");
		return SUCCESS;
	}
	
	public String alterdInfo() {
		Staff s = new Staff();
		s.setNumber(userRequest.getNumber());
		s.setCity(userRequest.getCity());
		s.setCountry(userRequest.getCountry());
		s.setEmail(userRequest.getEmail());
		s.setEntryDate(userRequest.getEntryDate());
		s.setFirstName(userRequest.getFirstName());
		s.setLastName(userRequest.getLastName());
		s.setProvince(userRequest.getProvince());
		s.setZipCode(userRequest.getEntryDate());
		staffInfo.alterStaffByNumber(s);
		return getInfo();
	}
}
