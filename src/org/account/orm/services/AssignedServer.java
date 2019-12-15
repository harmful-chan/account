package org.account.orm.services;

import org.account.orm.bean.*;
import org.account.util.HibernateUtil;


public class AssignedServer {
	
	
	/**
	 * 判断是否已分配的资源
	 * @param role 
	 * @param permission
	 * @param node
	 * @return
	 */
	public boolean isAssign(Role role, Permission permission, Node node) {
		if(role == null && node == null) return false;
		if(role == null) {
			return permission.getNodes().contains(node);
		}else if(node == null) {
			return role.getPermissions().contains(permission);
		}else {
			return role.getPermissions().contains(permission) && permission.getNodes().contains(node);
		}
	}
	

	
	public void addAssign(Role role, Permission permission, Node node) {

		if(role == null && node == null) {
			return ;
		}else if(role == null) {
			permission.getNodes().add(node);
		}else if(node == null) {
			role.getPermissions().add(permission);
		}else {
			permission.getNodes().add(node);
			role.getPermissions().add(permission);
		}
	}
	
	public void deleteAssign(Role role, Permission permission, Node node) {
		if(role == null && node == null) {
			return;
		}else if(role == null) {
			permission.getNodes().remove(node);
		}else if(node == null) {
			role.getPermissions().remove(permission);
		}else {
			permission.getNodes().remove(node);
			role.getPermissions().remove(permission);
		}
	}
}

