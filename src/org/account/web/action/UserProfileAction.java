package org.account.web.action;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.SystemManagement;
import org.account.orm.model.*;
import org.account.web.viewmodel.TableViewModel;
import org.account.web.viewmodel.User;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserProfileAction  extends ActionBase implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;

	private User input = new User();
	@Override
	public User getModel() {
		return this.input;
	}
	
	public String getInfo() {
		User uvm = new User();
		Staff  staff = arbitrate.getStaff(arbitrate.getCurrentActiveStaff().getNumber());
		uvm.setAccount(input);
		request.setAttribute("user_view_model", staff);
		return "user";
	}
	
	public String alterdInfo() {
	
		arbitrate.alterStaff(inputStaff.getNumber(), inputStaff.getFirstName(), inputStaff.getLastName(), inputStaff.getCity(), 
				inputStaff.getProvince(), inputStaff.getCountry(), inputStaff.getZipCode(), inputStaff.getEntryDate());
		
		return getInfo();
	}
}
