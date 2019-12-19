package org.account.orm.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.account.orm.bean.*;
import org.account.orm.impl.AccountImpl;
import org.account.orm.impl.DepartmentImpl;
import org.account.util.HibernateUtil;
import org.apache.commons.codec.binary.Base64;

public class SecretServer {
	private AccountImpl  ai = null;
	private DepartmentImpl  di = null;
	private static final int TIME_OUT = 30;
	
	
	public SecretServer() {
		this.ai = new AccountImpl();
		this.di = new DepartmentImpl();
	}
	
	
	public Account getAccount(String accountNumber) {
		return (Account)HibernateUtil.queryOnly("FROM Account a WHERE a.number='"+accountNumber+"'");
	}
	
	public String getAccountPassword(String number) {
		Account account = this.ai.findByNumber(number);
		return account.getPassword();
	}
	
	
	/**
	 * 更新账号信息
	 * @param account
	 */
	public void updateAccount(Account account) {
		this.ai.updateAccount(account);
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
		try {
			String curr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
			String salf = account.getSalf().substring(0, 8);
			return (Long.parseLong(curr) - Long.parseLong(salf)) < TIME_OUT;
		}catch(Exception e) {
			return false;
		}
		
	}

	
	public String encode(String password, String self) {
		try {
			return new String(Base64.encodeBase64((password+self).getBytes(), true));	
		}catch(Exception e) {
			return null;
		}
	}

	public String decode(String deeppwd) {
		try {
			return new String(Base64.decodeBase64(deeppwd));
		}catch(Exception e) {
			return null;
		}
	}
	
	public String decodePassword(String deeppwd) {
		//密码解析
		String deep = decode(deeppwd);
		String salf = deep.substring(deep.length()-17, deep.length());
		String password = deep.replaceAll(salf, "");
		return password;
	}
	
	public String decodeSalf(String deeppwd) {
		//密码解析
		String deep = decode(deeppwd);
		String salf = deep.substring(deep.length()-17, deep.length());
		return salf;
	}
	
}
