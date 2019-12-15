package org.account.orm.impl;

import org.account.orm.bean.Role;
import org.account.util.HibernateUtil;

public class RoleImpl {
	public Role findByName(String name) {
		Role ret = (Role)HibernateUtil.queryOnly("FROM Role r WHERE r.name='"+name+"'");
		return ret;
	}

}
