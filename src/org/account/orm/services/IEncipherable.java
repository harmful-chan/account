package org.account.orm.services;

public interface IEncipherable {
	public String encrypt(String password, String self);    //����
	public String decode(String deeppwd, String self);    //����
}
