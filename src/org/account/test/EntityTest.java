package org.account.test;
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
		Configuration cfg = new Configuration().configure();
		
		//建立会话工厂
		SessionFactory sf = cfg.buildSessionFactory();
		
		//打开连接
		Session session = sf.openSession();
		
		//开启事务连接
		Transaction trans = session.getTransaction();
		trans.begin();
		//保存数u
		System.out.println("123");
		
		//提交事务
		trans.commit();
		
		//关闭会话
		session.close();
		
		//释放工厂
		sf.close();
		
	}

}
