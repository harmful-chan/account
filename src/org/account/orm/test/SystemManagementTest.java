package org.account.orm.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.account.orm.SystemManagement;
import org.junit.Test;

public class SystemManagementTest  extends TestBase {
	private SystemManagement entity;
	public SystemManagementTest() {
		super();
		this.entity = new SystemManagement();
	}
	@Test
	public void ShouldAddActiveStaffUsable() {
		assertTrue(this.entity.addActiveStaff("A000000000") > 0); 
	}
	@Test
	public void ShouldRemoveActiveStaffUsable() {
		this.entity.addActiveStaff("A000000000");
		assertTrue(this.entity.removeActiveStaff("A000000000") > 0); 
	}
	
	@Test
	public void ShouldIsActiveStaffUsable() {
		this.entity.addActiveStaff("A000000000");
		assertTrue(this.entity.isActiveStaff("A000000000")); 
	}
	
	@Test
	public void ShouldIGetActiveStaffUsable() {
		this.entity.addActiveStaff("A000000000");
		assertTrue(this.entity.getActiveStaffs().size() > 0); 
	}
	
	@Test
	public void ShouldISetCurrentActiveStaffUsable() {
		this.entity.addActiveStaff("A000000000");
		assertTrue(this.entity.setCurrentActiveStaff("A000000000") > 0); 
	}
	
	
	@Test
	public void ShouldIGetCurrentActiveStaffUsable() {
		this.entity.addActiveStaff("A000000000");
		this.entity.setCurrentActiveStaff("A000000000");
		assertNotNull(this.entity.getCurrentActiveStaff());;
	}
}
