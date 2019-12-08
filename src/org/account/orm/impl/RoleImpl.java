package org.account.orm.impl;

import org.account.orm.model.Role;
import org.account.util.HibernateUtil;

public class RoleImpl extends EntityImpl<Role> {

	public Role findById(int id) {
		return (Role)HibernateUtil.get(Role.class, id);
	}

	public Role findByName(String name) {
		Role ret = (Role)HibernateUtil.queryOnle("FROM Role r WHERE r.name='"+name+"'");
		return ret;
	}

}
