package org.account.orm.test;

import org.account.util.HibernateUtil;

public abstract class TestBase {
	public TestBase(){
		new HibernateUtil();
	}
}
