package org.account.orm.test;

import java.util.List;

import org.account.orm.impl.*;
import org.account.orm.model.Node;
import org.account.orm.model.Role;
import org.account.orm.services.StaffInfoServer;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class ImplTest {
	private RoleImpl ri = null;
	public ImplTest() {
		HibernateUtil.init();
		this.ri = new RoleImpl();
	}
	
	
	
	@Test
	public void ShouldFindByName(){
		ri.findByName("root");
		
	}
	
	@Test
	public void ShouldNode() {
		NodeImpl node = new NodeImpl();
		List<Node> ns = node.findPrivateList();
	}
	
	@Test
	public void ShouldStaff() {
		//StaffInfoServer sis = new StaffInfoServer();
		//sis.getNodes("A000010000");
		
		
		StaffImpl si = new StaffImpl();
		
		si.findPrimissionsByNumber("A000010000");
	}
	
}
