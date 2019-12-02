package org.account.orm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.account.orm.AllocateDepartmentAccountManagement;
import org.account.orm.model.Department;
import org.junit.Test;

public class AllocateDepartmentAccountManagementTest extends TestBase {
	
	private AllocateDepartmentAccountManagement entity;
	public AllocateDepartmentAccountManagementTest() {
		super();
		this.entity = new AllocateDepartmentAccountManagement();
	}
	
	@Test
	public void ShouldGetUsable() {
		assertTrue(entity.getDepartmentAccounts(Department.DIRECTOR).size() > 0); 
	}
	
	@Test
	public void ShouldAddUsable() {
		assertTrue(entity.addDepartmentAccount(Department.DIRECTOR, "0000000001", "000001", "20191202", "��Ӳ����ʺ�") > 0); 
	}
	
	@Test
	public void ShouldAlterUsable() {
		assertTrue(entity.alterDepartmentAccount(Department.DIRECTOR, "0000000001", "000001", "20191202", "��Ӳ����ʺ�2") > 0); 
	}
	
	@Test
	public void ShouldRemoveUsable() {
		assertTrue(entity.removeDepartmentAccount(Department.DIRECTOR, "0000000001") > 0); 
	}
	
	@Test
	public void ShouldGetAccountPasswordeUsable() {
		assertEquals(entity.getAccountPassword("0000000000"), "000000");
	 
	}
	
	@Test
	public void ShouldGetAccountUsersUsable() {
		assertTrue(entity.getAccountUsers("0000000000").size() > 0);
	 
	}	
	
	@Test
	public void ShouldAddAccountUsable() {
		assertTrue(entity.addAccount("000000002", "000002", "20191202", "ShouldAddAccountUsable") > 0);
	 
	}	
	
	@Test
	public void ShouldremoveAccountUsable() {
		assertTrue(entity.removeAccount("000000002") > 0);
	}
	
	@Test
	public void ShouldIsValidUsable() {
		assertTrue(entity.isValid("0000000000"));
	}	

	@Test
	public void ShouldUpdateValidUsable() {
		assertTrue(entity.updateValid("0000000000") > 0);
	}	
	
	@Test
	public void ShouldGetSaltUsable() {
		assertEquals(entity.getSalt("0000000000"), "20191202");
	}	
}
