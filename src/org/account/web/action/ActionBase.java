package org.account.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.services.ActiveServer;
import org.account.orm.services.EncryptedServer;
import org.account.orm.services.SecretServer;
import org.account.orm.services.StaffInfoServer;

import com.opensymphony.xwork2.ActionSupport;

public class ActionBase extends ActionSupport implements IActionable{


	
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> session;
	protected ActiveServer<String> active;
	protected EncryptedServer encryption;
	protected SecretServer secret;
	protected StaffInfoServer staffInfo;

	@Override
	public void setSessionServer(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setActiveServer(ActiveServer<String> active) {
		this.active = active;
	}

	@Override
	public void setEncryptedServer(EncryptedServer encryption) {
		this.encryption = encryption;
	}

	@Override
	public void setSecretServer(SecretServer secret) {
		this.secret = secret;
	}

	@Override
	public void setStaffInfoServer(StaffInfoServer staffInfo) {
		this.staffInfo = staffInfo;
	}

}
