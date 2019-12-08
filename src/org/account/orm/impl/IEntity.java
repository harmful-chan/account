package org.account.orm.impl;

public interface IEntity<T> {

	public int add(T model);
	public int modify(T model);
	public int remove(T model);
}
