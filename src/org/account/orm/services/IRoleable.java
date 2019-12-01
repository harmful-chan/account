package org.account.orm.services;


public interface IRoleable {
	public String getRoleName(String number);
	public int Award(String number, String roleName);
}
