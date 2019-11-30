package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	private int id;
	private int permission;
	private String explain;
	private Set<Node> nodes = new HashSet<Node>();
	private Set<Role> roles = new HashSet<Role>();
    
    
	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
    
    
    
    
}
