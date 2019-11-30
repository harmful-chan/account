package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private int id;
	private String node;
	private boolean idPrivate;
	private Set<Permission> permissions = new HashSet<Permission>();
	
	
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public boolean isIdPrivate() {
		return idPrivate;
	}
	public void setIdPrivate(boolean idPrivate) {
		this.idPrivate = idPrivate;
	}
	
	
}
