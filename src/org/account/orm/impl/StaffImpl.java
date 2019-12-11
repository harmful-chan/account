package org.account.orm.impl;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.*;
import org.account.util.HibernateUtil;

public class StaffImpl extends EntityImpl<Staff>  {
	public Staff findByNumber(String number) {
		//FROM  Staff s, Account a, Role r, Department d WHERE  s.number='"+number+"' a.id=s.account.id and r.id=s.role.id and d.id=s.department.id
		//return (Staff)HibernateUtil.queryOnle("FROM Staff s WHERE s.number='"+number+"'");
		Object[] ret = (Object[])HibernateUtil.queryOnly("FROM  Staff s,  Role r, Department d  WHERE  s.number='"+number+"' and r.id=s.role.id and d.id=s.department.id");
		return ret == null ? null: (Staff)ret[0];
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
	
	public void updateByNumber(Staff staff) {
		Staff s = findByNumber(staff.getNumber());
		
		s.setCity(staff.getCity());
		s.setCountry(staff.getCountry());
		s.setEmail(staff.getEmail());
		s.setEntryDate(staff.getEntryDate());
		s.setFirstName(staff.getFirstName());
		s.setLastName(staff.getLastName());
		s.setProvince(staff.getProvince());
		s.setZipCode(staff.getZipCode());
		modify(s);
	}
	
	public List<Staff> findHold(){
		List<Object[]> result = (List<Object[]>)HibernateUtil.queryList("FROM  Staff s, Account a, Role r, Department d WHERE a.id=s.account.id and r.id=s.role.id and d.id=s.department.id");
		List<Staff> ret = new ArrayList<Staff>();
		result.forEach(o -> ret.add((Staff)o[0]));
		return ret;
	}
	
}
