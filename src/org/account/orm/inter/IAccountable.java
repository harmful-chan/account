package org.account.orm.inter;

import java.util.List;

public interface IAccountable {
	
	public boolean isValid(String account);
	public int updateValid(String account);
	public String getSalt(String account);
	
	public String getAccount(String number);
	public String getAccountPassword(String account);
	public  List<String> getAccountUsers(String account);
	public int setAccountUser(String account);
	public int addAccount(String account, String password, String salf, String explain);
	public int removeAccount(String account);
}
