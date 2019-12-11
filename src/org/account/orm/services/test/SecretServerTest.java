package org.account.orm.services.test;

import java.util.List;

import org.account.orm.model.Account;
import org.account.orm.services.SecretServer;
import org.account.util.HibernateUtil;
import org.junit.Test;


public class SecretServerTest {
	private SecretServer ss;

	public SecretServerTest() {
		HibernateUtil.init();
		this.ss = new SecretServer();
	}
	

}
