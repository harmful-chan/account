package org.account.orm.impl;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.*;
import org.account.util.HibernateUtil;

public class StaffImpl {
	public Staff findByNumber(String number) {
		return (Staff)HibernateUtil.queryOnle("FROM Staff s WHERE s.number='"+number+"'");
	}
	
	
	public Role findRoleByNumber(String number) {
		Object[] ret =  (Object[])HibernateUtil.queryOnle("FROM Staff s LEFT JOIN s.role   WHERE s.number='"+number+"'");
		return (Role)ret[1];
	}
	
	public Department findDepartmentByNumber(String number) {
		Object[] ret =  (Object[])HibernateUtil.queryOnle("FROM Staff s LEFT JOIN s.department  WHERE s.number='"+number+"'");
		return (Department)ret[1];
	}
	
	public List<Permission> findPrimissionsByNumber(String number) {
		List<Object[]> objectsList =  (List<Object[]>)HibernateUtil.queryList("FROM Staff s LEFT JOIN s.role r LEFT JOIN r.permissions  WHERE s.number='"+number+"'");
		List<Permission> ret = new ArrayList<Permission>();
		for (Object[] objects : objectsList) {
			ret.add((Permission)objects[2]);
		}
		return ret;
	}
	
	public List<Node> findNodesByNumber(String number) {
		List<Object[]> objectsList =  (List<Object[]>)HibernateUtil.queryList("FROM Staff s LEFT JOIN s.role r LEFT JOIN r.permissions p LEFT JOIN p.nodes  WHERE s.number='"+number+"'");
		List<Node> ret = new ArrayList<Node>();
		for (Object[] objects : objectsList) {
			ret.add((Node)objects[3]);
		}
		return ret;
	}
	
	
}
