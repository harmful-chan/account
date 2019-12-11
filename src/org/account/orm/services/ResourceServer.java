package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.impl.NodeImpl;
import org.account.orm.model.Node;

public class ResourceServer {
	private NodeImpl nodeImpl = null;
	
	public ResourceServer() {
		this.nodeImpl = new NodeImpl();
	}
	
	
	public  boolean isAny(List<Node> nodeList, String url) {
		List<String> ns = new ArrayList<String>();
		nodeList.forEach(n -> ns.add(n.getNode()));
		return ns.contains(url);
	}
	
	public boolean isPublicResource(String url) {
		List<Node> nodes =  this.nodeImpl.findPublicList();
		return isAny(nodes, url);
	}
	
	public boolean isPrivateResource(String url) {
		List<Node> nodes = this.nodeImpl.findPrivateList();
		return isAny(nodes, url);
	}
}
