package org.account.web.action;

import java.util.List;

import org.account.orm.bean.*;
import org.account.web.bean.UserContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

public class UserAction  extends ActionBase implements ModelDriven<UserContext> {
	private static final long serialVersionUID = 1L;

	private UserContext userRequest = new UserContext();
	@Override
	public UserContext getModel() {
		return this.userRequest;
	}
	
	@SkipValidation
	public String getInfo() throws Exception {
		
		Staff  staff = info.getHoldStaffInfo(active.getCurrent().getNumber());
		List<Permission> permissions = info.getStaffPermissions(staff.getNumber());    //获取权限码
		
		UserContext uvm = new UserContext();
		uvm.setOperator(staff.getNumber());
		uvm.setAccountNumber(staff.getAccount().getNumber());
		uvm.setRole(staff.getRole().getExplain());
		uvm.setDepartment(staff.getDepartment().getExplain());
		uvm.setEntryDate(staff.getEntryDate());
		uvm.setCountry(staff.getCountry());
		uvm.setProvince(staff.getProvince());
		uvm.setCity(staff.getCity());
		uvm.setZipCode(staff.getZipCode());
		uvm.setFirstName(staff.getFirstName());
		uvm.setLastName(staff.getLastName());
		uvm.setEmail(staff.getEmail());
		uvm.setPermissioms(permissions.toString());
		session.put("user_info", uvm);
		session.put("user_update_url", "http://localhost:8080/account/User/alterdInfo");
		flashUrl(staff);
		return SUCCESS;
	}
	
	public String alterdInfo() throws Exception {
		Staff s = new Staff();
		s.setNumber(userRequest.getOperator());
		s.setCity(userRequest.getCity());
		s.setCountry(userRequest.getCountry());
		s.setProvince(userRequest.getProvince());
		s.setEmail(userRequest.getEmail());
		s.setEntryDate(userRequest.getEntryDate());
		s.setZipCode(userRequest.getZipCode());
		info.updateStaffInfo(s);
		return getInfo();
	}
}
