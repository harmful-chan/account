package org.account.orm.impl;

import org.account.orm.model.Account;
import org.account.orm.model.Department;
import org.account.util.HibernateUtil;

public class DepartmentImpl extends EntityImpl<Department>{

	public Department findByName(String name) {
		return (Department)HibernateUtil.queryOnly("FROM Department d WHERE d.name='"+name+"'");
	}
}
