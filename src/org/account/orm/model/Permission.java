package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	private Integer id;
	private Integer permission;
	private String explain;
	private Set<Node> nodes = new HashSet<Node>();
	private Set<Role> roles = new HashSet<Role>();
    
	public Permission(){
		
	}
	public Permission(int p, String e) {
		this.permission = p;
		this.explain = e;
	}
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
    
    
    
    
}
