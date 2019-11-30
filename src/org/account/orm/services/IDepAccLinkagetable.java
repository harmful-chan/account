package org.account.orm.services;

import java.util.List;

import org.account.orm.model.Account;

public interface IDepAccLinkagetable {
	public List<Account> getDepartmentAccounts(String department);
	public int addDepartmentAccount(String department, String account, String password, String salf, String explain);
	public int alterDepartmentAccount(String department, String account, String password, String salf, String explain);
	public int removeDepartmentAccount(String department, String account);


}
