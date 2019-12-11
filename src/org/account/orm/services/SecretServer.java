package org.account.orm.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.account.orm.impl.AccountImpl;
import org.account.orm.impl.DepartmentImpl;
import org.account.orm.model.*;

public class SecretServer {
	private AccountImpl  ai = null;
	private DepartmentImpl  di = null;
	private static final int TIME_OUT = 30;
	
	
	public SecretServer() {
		this.ai = new AccountImpl();
		this.di = new DepartmentImpl();
	}
	
	
	public String getAccountPassword(String number) {
		Account account = this.ai.findByNumber(number);
		return account.getPassword();
	}
	
	
	
	/**
	 * 把Account添加进部门中
	 * @param account
	 * @param departments
	 */
	public void addShare(Account account, String... departmentNames) {
		Account staffAccount = this.ai.findByNumber(account.getNumber());
		for (String departmentName : departmentNames) {
			Department department = this.di.findByName(departmentName);
			if(staffAccount != null) {
				department.getAccounts().add(account);	
			}else {
				this.ai.add(account);
				Account a = this.ai.findByNumber(account.getNumber());
				department.getAccounts().add(a);
			}
			this.di.modify(department);
		}
		
	}
	
	
	/**
	 * 获取部门共享Account
	 * @param department
	 * @return
	 */
	public List<Account> getShare(String departmentName){

		
		Department d = this.di.findByName(departmentName);
		return new ArrayList<Account>(d.getAccounts());
	}
	
	
	
	/**
	 * 更新账号信息
	 * @param account
	 */
	public void updateAccount(Account account) {
		this.ai.updateAccount(account);
	}
	
	
	/**
	 * 吧账号从部门移除
	 * @param accountNumber
	 * @param departmentName
	 */
	public void removeShare(String accountNumber, String departmentName) {
		Department d = this.di.findByName(departmentName);
		Account a = this.ai.findByNumber(accountNumber);
		d.getAccounts().remove(a);
		this.di.modify(d);
	}
	
	/**
	 * 判断账号是否属于部门
	 * @param accountNumber
	 * @param departmentName
	 * @return
	 */
	public boolean isShare(String accountNumber,String departmentName) {
		
		Department d = this.di.findByName(departmentName);
		Account a = this.ai.findByNumber(accountNumber);
		
		boolean flag = d.getAccounts().contains(a);
		return flag;
	}
	
	
	
	private long compareDate(String s1, String s2) {
		return Long.parseLong(s1) - Long.parseLong(s2);
	}
	
	
	/**
	 * 判断账号是否有效
	 * @param account
	 * @return
	 */
	public boolean isValid(Account account) {
		String curr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
		return compareDate(curr,  account.getSalf().substring(0, 7)) < TIME_OUT;
	}

//	void addSpecific(Account, Staff... s);
//	public List<Staff> getSpecifics(){
//		
//	}
	
//	void removeSpecific(Account, staff)
	
}
