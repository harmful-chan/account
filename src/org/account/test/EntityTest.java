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
		//��ȡ���������ļ�Ĭ��:src/hibernate.cfg.xml
		Configuration cfg = new Configuration().configure();
		
		//�����Ự����
		SessionFactory sf = cfg.buildSessionFactory();
		
		//������
		Session session = sf.openSession();
		
		//������������
		Transaction trans = session.getTransaction();
		trans.begin();
		//������u
		System.out.println("123");
		
		//�ύ����
		trans.commit();
		
		//�رջỰ
		session.close();
		
		//�ͷŹ���
		sf.close();
		
	}

}
