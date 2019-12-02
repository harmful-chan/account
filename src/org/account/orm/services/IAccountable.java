package org.account.orm.services;

import java.util.List;

public interface IAccountable {
	public String getAccountPassword(String account);
	public  List<String> getAccountUsers(String account);
	public int addAccount(String account, String password, String salf, String explain);
	public int removeAccount(String account);
	public boolean isValid(String account);
	public int updateValid(String account);
	public String getSalt(String account);
}
