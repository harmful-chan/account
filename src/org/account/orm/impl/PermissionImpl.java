package org.account.orm.impl;

import org.account.orm.bean.Permission;
import org.account.util.HibernateUtil;

public class PermissionImpl{
	public Permission findByCode(int code) {
		return (Permission)HibernateUtil.queryOnly("FROM Permission p WHRER p.code='"+code+"'");
	}
}
