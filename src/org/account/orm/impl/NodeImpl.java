package org.account.orm.impl;

import java.util.List;

import org.account.orm.model.Node;
import org.account.util.HibernateUtil;

public class NodeImpl {
	public List<Node> findPrivateList(){
		return (List<Node>)HibernateUtil.queryList("FROM Node n WHERE n.isPrivate='true'");
	}
	
	public List<Node> findPublicList(){
		return (List<Node>)HibernateUtil.queryList("FROM Node n WHERE n.isPrivate='false'");
	}
}
