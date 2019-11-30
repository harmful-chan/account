package org.account.orm.services;

public interface IAccountable {
	public String getAccountPassword(String account);
	public String getAccountUsers(String account);
	public int addAccount(String account, String password, String salf);
	public int removeAccount(String account);
	public boolean isValid(String account);
	public int setValid(String account);
	public String getSalt();
}
