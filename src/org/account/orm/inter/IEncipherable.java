package org.account.orm.inter;

public interface IEncipherable {
	public String encrypt(String password, String self);    //����
	public String decode(String deeppwd);    //����
}
