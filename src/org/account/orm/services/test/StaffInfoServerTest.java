package org.account.orm.services.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.account.orm.model.Account;
import org.account.orm.model.Staff;
import org.account.orm.services.StaffInfoServer;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class StaffInfoServerTest {
	
	private StaffInfoServer sis;
	public StaffInfoServerTest() {
		HibernateUtil.init();
		this.sis = new StaffInfoServer();
	}
	@Test
	public void ShouldGetHold() {
		List<Staff> ret = this.sis.getHold();
		assertNotNull(ret);
	}
	
	@Test
	public void ShouldGetStaff() {
		Staff ret = this.sis.getStaff("A000010000");
		assertNotNull(ret);
	}
}
