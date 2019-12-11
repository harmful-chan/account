package org.account.orm.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.account.orm.impl.AccountImpl;
import org.account.orm.impl.StaffImpl;
import org.account.orm.model.Staff;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class StaffImplTest {
	private StaffImpl si= null;
	public StaffImplTest() {
		HibernateUtil.init();
		this.si = new StaffImpl();
	}
	
	@Test
	public void ShouldFindByNumber() {
		assertNotNull(this.si.findByNumber("A000010000"));
	}
	
	
	@Test
	public void ShouldFindNodesByNumber() {
		assertNotNull(this.si.findNodesByNumber("A000010000"));
	}
	
	@Test
	public void ShouldFindPrimissionsByNumber() {
		assertNotNull(this.si.findPrimissionsByNumber("A000010000"));
	}
	
	@Test
	public void ShouldUpdateByNumber() {
		
		Staff staff = new Staff();
		staff.setNumber("A000010007");
		staff.setZipCode("xxxxxxxx");
		
		this.si.updateByNumber(staff);
		
		Staff s =this.si.findByNumber("A000010007");
		
		assertEquals(staff.getZipCode(), s.getZipCode());
	}
	
}
