package org.account.orm.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.account.orm.bean.Account;
import org.account.orm.bean.Department;
import org.account.orm.bean.Role;
import org.account.orm.bean.Staff;
import org.account.orm.services.InfoServer;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class InfoServerTest {
	
	private InfoServer sis;
	public InfoServerTest() {
		HibernateUtil.init();
		this.sis = new InfoServer();
	}
	
	@Test
	public void ShouldOther() {
	}
	
	@Test
	public void ShouldGetStaffInfo() {
		Staff ret = this.sis.getStaffInfo("A000010000");
		assertNotNull(ret);
	}
	
	@Test
	public void ShouldUpdateStaffInfo() throws Exception {
		Staff staff = new Staff("A000010030", "", "", "", "", "", "", "", "");
		this.sis.updateStaffInfo(staff);
		assertEquals(this.sis.getStaffInfo("A000010030").getFirstName(), "");
	}
	
	@Test
	public void ShouldGetDepartmentInfo() throws Exception {
		
		assertNotNull(this.sis.getDepartmentInfo(Department.MANAGERMENT));
	}
	
	@Test
	public void ShouldGetRoleInfo() throws Exception{
		
		assertNotNull(this.sis.getRoleInfo(Role.ROOT));
	}
	
	@Test
	public void ShouldGetStaffPermissions() throws Exception{
		
		assertNotNull(this.sis.getStaffPermissions("A000010030"));
	}
	
	
	@Test
	public void ShouldGetStaffNodes() throws Exception{
		
		assertNotNull(this.sis.getStaffNodes("A000010030"));
	}
	
	
	
	
	
	@Test
	public void ShouldGetHoldStaffInfo(){
		
		assertNotNull(this.sis.getHoldStaffInfo("A000010000"));
	}
	
	@Test
	public void ShouldGetHoldStaffList() throws Exception{
		
		assertNotNull(this.sis.getHoldStaffList());
	}
	
	@Test
	public void ShouldIsHoldStaff(){
		assertTrue(this.sis.isHoldStaff("A000010000"));
		assertTrue(!this.sis.isHoldStaff("A000010030"));
	}
	
	@Test
	public void ShouldGetHoldStaffAccount() throws Exception{
		assertNotNull(this.sis.getHoldStaffAccount("A000010000"));
	}
	
	@Test
	public void ShouldAddHoldStaff() throws Exception{
		Account account = new Account("0000000010", "000010", "000000000000000", "测试账号");
		this.sis.addHoldStaff("A000010030", account);
		assertTrue(this.sis.isHoldStaff("A000010030"));
	}
	
	@Test
	public void ShouldRemoveHoldStaff() throws Exception{
		this.sis.removeHoldStaffAccount("A000010030");
		assertTrue(!this.sis.isHoldStaff("A000010030"));
	}
	
	
	
	
	
	@Test
	public void ShouldGetShareAccountList() throws Exception{
		List<Account> ret = this.sis.getShareAccountList(Department.MANAGERMENT);
		assertNotNull(ret);
	}
	
	@Test
	public void ShouldIsShareAccount() throws Exception {
		assertTrue(this.sis.isShareAccount("0000000003", Department.MANAGERMENT)); 
	}
	@Test
	public void ShouldAddShareAccount() throws Exception {
		this.sis.addShareAccount(new Account("0000000090",  "000090", "20191213183252222", "测试账号"), Department.MANAGERMENT);
		assertTrue(this.sis.isShareAccount("0000000090", Department.MANAGERMENT));
	}
	
	@Test
	public void ShouldRemoveShareAccount() throws Exception {
		this.sis.removeShareAccount("0000000090", Department.MANAGERMENT);
		assertTrue(!this.sis.isShareAccount("0000000090", Department.MANAGERMENT));
	}
	
	
	//活动域， 用户组，管理文件系统，动态磁盘，配置打印服务，dns，dhcp，web
}
