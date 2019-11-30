package org.account.orm.services;

public interface IEncipherable {
	public String encrypt(String password, String self);    //º”√‹
	public String decode(String deeppwd, String self);    //Ω‚√‹
}
