package org.account.orm.impl;

import org.account.orm.model.Permission;
import org.account.util.HibernateUtil;

public class PermissionImpl extends EntityImpl<Permission> {
	public Permission findByCode(int code) {
		return (Permission)HibernateUtil.queryOnle("FROM Permission p WHRER p.code='"+code+"'");
	}
}
