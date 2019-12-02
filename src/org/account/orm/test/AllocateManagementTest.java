package org.account.orm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.account.orm.AllocateManagement;
import org.account.orm.model.Role;
import org.account.orm.services.INodeable;
import org.account.orm.services.IPermissionable;
import org.account.orm.services.IRoleable;
import org.junit.Test;

public class AllocateManagementTest extends TestBase{
	
	private AllocateManagement entity;
	public AllocateManagementTest() {
		super();
		this.entity = new AllocateManagement();
	}
	
	@Test
	public void SholdGetNodesUsable() {
		INodeable o = (INodeable)entity;
		assertTrue(o.getNodes().size() > 0);
	}
	
	@Test
	public void SholdGetPrivateUsable() {
		INodeable o = (INodeable)entity;
		assertTrue(o.getPrivates().size() > 0);
	}
	@Test
	public void ShouldIsNodeUsable() {
		INodeable o = (INodeable)entity;
		assertTrue(o.isNode("http://localhost:8080/account"));
	}
	@Test
	public void ShouldIsAnyUsable() {
		INodeable o = (INodeable)entity;
		assertTrue(o.isAny(o.getNodes(), "http://localhost:8080/account/Home/login"));
	}
	
	@Test
	public void SholdGetPermissionUsable() {
		IPermissionable o = (IPermissionable)entity;
		assertTrue(o.getPermission(Role.ROOT).length > 0);
	}
	
	@Test
	public void SholdGetRoleNameUsable() {
		IRoleable o = (IRoleable)entity;
		assertEquals(o.getRoleName("A000000000"), Role.ROOT);
	}
	@Test
	public void SholdAwardUsable() {
		IRoleable o = (IRoleable)entity;
		assertTrue(o.Award("A000000000", Role.ROOT) > 0);
	}
	
	@Test
	public void SholdIsResourceUsable() {
		assertTrue(entity.isResource("http://localhost:8080/account/Home/login"));
	}
	
	@Test
	public void SholdisPrivateResourceUsable() {
		assertTrue(entity.isPrivateResource("http://localhost:8080/account/Home/deplan"));
	}
	
	@Test
	public void SholdGetStaffPermissionsUsable() {
		assertTrue(entity.getStaffPermissions("A000000000").length > 0);
	}
	
	
	@Test
	public void SholdGetPermissionResourcesUsable() {
		assertTrue(entity.getPermissionResources(30).size() > 0);
	}
	
	@Test
	public void SholdIsAllowUsable() {
		assertTrue(entity.isAllow("A000000000", "http://localhost:8080/account/AccountTable/removeRoot"));
	}
}
