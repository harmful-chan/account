package org.account.orm.impl;

import org.account.util.HibernateUtil;

public class EntityImpl<T> implements  IEntity<T>{
	

	@Override
	public int add(T model) {
		return HibernateUtil.save(model);
	}

	@Override
	public int modify(T model) {
		return HibernateUtil.save(model);
	}

	@Override
	public int remove(T model) {
		return HibernateUtil.delete(model);
	}
	
}
