package org.account.orm.services;

import org.account.orm.model.*;
import org.account.util.HibernateUtil;


public class AssignedServer {
	
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
	

	
	public boolean addAssign(Role role, Permission permission, Node node) {
		int ret = 0;
		if(role == null && node == null) {
			return false;
		}else if(role == null) {
			permission.getNodes().add(node);
			ret += HibernateUtil.save(node);
			ret += HibernateUtil.save(permission);
			return ret == 2;
		}else if(node == null) {
			role.getPermissions().add(permission);
			ret += HibernateUtil.save(permission);
			ret += HibernateUtil.save(role);
			return ret == 2;
		}else {
			permission.getNodes().add(node);
			role.getPermissions().add(permission);
			ret += HibernateUtil.save(node);
			ret += HibernateUtil.save(permission);
			ret += HibernateUtil.save(role);
			return ret == 3;
		}
	}
	
	public boolean deleteAssign(Role role, Permission permission, Node node) {
		int ret = 0;
		if(role == null && node == null) {
			return false;
		}else if(role == null) {
			permission.getNodes().remove(node);
			ret += HibernateUtil.save(node);
			ret += HibernateUtil.save(permission);
			return ret == 2;
		}else if(node == null) {
			role.getPermissions().remove(permission);
			ret += HibernateUtil.save(permission);
			ret += HibernateUtil.save(role);
			return ret == 2;
		}else {
			permission.getNodes().remove(node);
			role.getPermissions().remove(permission);
			ret += HibernateUtil.save(node);
			ret += HibernateUtil.save(permission);
			ret += HibernateUtil.save(role);
			return ret == 3;
		}
	}
}

