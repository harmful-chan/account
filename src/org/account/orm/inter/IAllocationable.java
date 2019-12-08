package org.account.orm.inter;

import java.util.List;

public interface IAllocationable extends INodeable, IPermissionable, IRoleable {
	public boolean isResource(String url);   
	public boolean isPrivateResource(String url); 
	public int[] getStaffPermissions(String number);
	public List<String> getPermissionResources(int[] permissions);
	boolean isAllow(String url);
}
