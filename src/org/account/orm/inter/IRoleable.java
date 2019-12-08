package org.account.orm.inter;


public interface IRoleable {
	public String getRoleName(String number);
	public int Award(String number, String roleName);
}
