package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private Integer id;
	private String name;
	private String node;
	private Boolean isPrivate;
	private Set<Permission> permissions = new HashSet<Permission>();
	
	
	public Node() {
		
	}
	
	public Node(String name, Boolean f, String n) {
		this.name = name;
		this.isPrivate = f;
		this.node = n;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}


	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", node=" + node + ", isPrivate=" + isPrivate + "]";
	}
	
}
