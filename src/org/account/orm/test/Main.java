package org.account.orm.test;

import org.account.orm.impl.EntityImpl;
import org.account.orm.model.Account;
import org.account.util.HibernateUtil;

public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HibernateUtil();
		//HibernateUtil.getSession().getTransaction().begin();
		
		//Account ac = (Account)HibernateUtil.getSession().createQuery("select a from Account a where a.id=2 ").uniqueResult();
		
		//HibernateUtil.getSession().getTransaction().commit();
		//HibernateUtil.getSession().close();

	}

}
