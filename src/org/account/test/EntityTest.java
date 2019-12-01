package org.account.test;
import org.account.orm.HibernateUtil;
import org.account.orm.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class EntityTest {
	
//	public static void main(String[] args) {
//		new EntityTest().ShouldHibernateUsable();
//	}
//	
	@Test
	public void ShouldHibernateUsable() {
		//获取核心配置文件默认:src/hibernate.cfg.xml
		
		HibernateUtil h1 =new HibernateUtil();
		
	}

}
