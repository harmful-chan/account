package org.account.web.action;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.SystemManagement;
import org.account.orm.model.SimpleStaff;

import com.opensymphony.xwork2.ActionSupport;

public class ActionBase extends ActionSupport implements IActionable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SystemManagement arbitrate;
	protected HttpServletRequest request;
	@Override
	public void setSystemManagement(SystemManagement sm) {
		this.arbitrate = sm;
		
	}
	@Override
	public void setRequest(HttpServletRequest request) {
		this.request =request;
	}

}
