package org.account.orm.impl.test;

import static org.junit.Assert.assertNotNull;

import org.account.orm.impl.AccountImpl;
import org.account.orm.model.Department;
import org.account.util.HibernateUtil;
import org.junit.Test;

public class AccountImplTest {
	private AccountImpl ai= null;
	public AccountImplTest() {
		HibernateUtil.init();
		this.ai = new AccountImpl();
	}
	@Test
	public void ShouldFindByNumber() {
		assertNotNull(this.ai.findByNumber("A000010000"));
		
	}
	
	@Test
	public void ShouldFindAccountsByDepartmentName() {
		//assertNotNull(this.ai.findAccountsByDepartmentName(Department.MARKER));
		
	}
}
