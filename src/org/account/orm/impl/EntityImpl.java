package org.account.orm.impl;

import org.account.util.HibernateUtil;

public class EntityImpl<T> {
	
	public int add(T model) {
		return HibernateUtil.save(model);
	}

	public int modify(T model) {
		return HibernateUtil.update(model);
	}

	public int remove(T model) {
		return HibernateUtil.delete(model);
	}
	
}
