package org.account.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.SystemManagement;
import org.account.orm.model.SimpleStaff;

import com.opensymphony.xwork2.ActionSupport;

public class ActionBase extends ActionSupport implements IActionable{

	/**
	 * 
	 */
	
	public static final String PRIMARY  = "primary";
	public static final String INFO  = "info";
	public static final String SUCESS  = "success";
	public static final String WARNING  = "warning";
	public static final String DANGER  = "danger";
	
	private static final long serialVersionUID = 1L;
	protected SystemManagement arbitrate;
	protected HttpServletRequest request;
	protected Map<String, Object> session;
	@Override
	public void setSystemManagement(SystemManagement sm) {
		this.arbitrate = sm;
		
	}
	@Override
	public void setRequest(HttpServletRequest request) {
		this.request =request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
