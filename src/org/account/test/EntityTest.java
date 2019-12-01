package org.account.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.account.orm.AllocateManagement;
import org.account.orm.model.*;
import org.account.orm.services.*;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class EntityTest {
	
	private Object entity;
	public EntityTest(){
		new HibernateUtil();
		this.entity = new AllocateManagement();
		System.out.println("³õÊ¼»¯²âÊÔ³ÌÐò!!!!!!!!!!!!!!!!!");
	}
	
	@Test
	public void SholdIRoleUsable() {
		IRoleable o = (IRoleable)entity;
		assertEquals(o.getRoleName("A000000000"), Role.ROOT);
		assertTrue(o.Award("A000000000", Role.ROOT) > 0);
	}
	
	@Test
	public void SholdIPermissionUsable() {
		IPermissionable o = (IPermissionable)entity;
		assertTrue(o.getPermission(Role.ROOT).length > 0);
	}
	
	@Test
	public void SholdINodeUsable() {
		INodeable o = (INodeable)entity;
		//assertTrue(o.getNodes().size() > 0);
		assertTrue(o.getPrivates().size() > 0);
	}


}
