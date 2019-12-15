package org.account.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.bean.Staff;
import org.account.orm.services.*;

public interface IActionable {

	public void setRequestServer(HttpServletRequest request);
	public void setSessionServer(Map<String, Object> session);
	public void setActiveServer(ActiveServer<Staff> active);
	public void setSecretServer(SecretServer secret);
	public void setInfoServer(InfoServer Info);
}
