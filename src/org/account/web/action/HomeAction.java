package org.account.web.action;

public class HomeAction extends ActionBase{
	private static final long serialVersionUID = 1L;
	public String home() {
		return SUCCESS;
	}
	public String login() {
		arbitrate.addActiveStaff(request.getParameter("number"));
		return SUCCESS;
	}
	
	public String deplan() {
		arbitrate.removeActiveStaff(request.getParameter("number"));
		return SUCCESS;
	}
}
