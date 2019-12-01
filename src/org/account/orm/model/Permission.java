package org.account.orm.model;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	private Integer id;
	private Integer code;
	private String explain;
	private Set<Node> nodes = new HashSet<Node>();
	private Set<Role> roles = new HashSet<Role>();
    
	public Permission(){
		
	}
	public Permission(Integer c, String e) {
		this.code = c;
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

	

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
    
    
    
    
}
