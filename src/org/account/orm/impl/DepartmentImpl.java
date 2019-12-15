package org.account.orm.impl;

import java.util.List;

import org.account.orm.bean.Account;
import org.account.orm.bean.Department;
import org.account.util.HibernateUtil;

public class DepartmentImpl{

	public Department findByName(String name) {
		List<Object[]> ret = (List<Object[]>)HibernateUtil.queryList("FROM Department d, Account a  WHERE d.name='"+name+"'");
		
		return (Department)ret.get(0)[0];
	}
}
