package org.account.orm.services;

public interface IPermissionable {
	public int[] getPermission(String roleName);
}
