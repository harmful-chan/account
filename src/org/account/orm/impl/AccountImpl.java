package org.account.orm.impl;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.Account;
import org.account.util.HibernateUtil;

public class AccountImpl extends EntityImpl<Account> {
	public Account findByNumber(String number) {
		return (Account)HibernateUtil.queryOnly("FROM Account a WHERE a.number='"+number+"'");
	}
	

	
	public void updateAccount(Account account) {
		Account a = (Account)HibernateUtil.queryOnly("FROM Account a WHERE a.number='"+account.getNumber()+"'");
		a.setPassword(account.getPassword());
		a.setExplain(account.getExplain());
		modify(a);
	}
}
