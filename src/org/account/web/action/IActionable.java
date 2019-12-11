package org.account.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.services.*;

public interface IActionable {

	public void setSessionServer(Map<String, Object> session);
	public void setActiveServer(ActiveServer<String> active);
	public void setEncryptedServer(EncryptedServer encryption);
	public void setSecretServer(SecretServer secret);
	public void setStaffInfoServer(StaffInfoServer staffInfo);
}
