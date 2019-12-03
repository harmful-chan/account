package org.account.web.action;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.SystemManagement;
import org.account.orm.model.SimpleStaff;

public interface IActionable {
	public void setSystemManagement(SystemManagement sm);
	public void setRequest(HttpServletRequest request);
}
