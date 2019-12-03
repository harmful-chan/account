package org.account.orm.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.account.orm.CompanyManagement;
import org.junit.Test;

public class CompanyManagementTest extends TestBase {
	private CompanyManagement entity;
	public CompanyManagementTest() {
		super();
		this.entity = new CompanyManagement();
	}
	
	@Test
	public void ShouldGetStaff() {
		assertNotNull(this.entity.getSalt("0000000000"));
	}
	
	@Test
	public void ShouldAddStaff() {
		assertTrue(this.entity.addStaff("A0000000001", "test", "test", "test", "test", "test", "test", "test") > 0);
	}
	
	@Test
	public void ShouldRemoveStaff() {
		assertNotNull(this.entity.removeStaff("A0000000001"));
	}
	
	@Test
	public void ShouldAlterStaff() {
		assertNotNull(this.entity.alterStaff("A000000000", "chan", "harmful", "guangzhou", "gaungdong", "china", "511400", "20191201"));
	}
	
}
