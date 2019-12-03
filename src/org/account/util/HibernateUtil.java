package org.account.util;

import java.util.HashMap;
import java.util.Map;

import org.account.orm.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	static{
		LogUtil.Log("[Hibernate]:��ȡ�����ļ�]");
		Configuration cfg = new Configuration().configure();
		LogUtil.Log("[Hibernate]:�����Ự����");
		sessionFactory = cfg.buildSessionFactory();
		LogUtil.Log("[Hibernate]:����д���������");
		initCoreData();
		LogUtil.Log("[Hibernate]:���ݽ�����������");
		initCoreRelation();
		LogUtil.Log("[Hibernate]:����д�������������");
		initTestDate();
	}
	
	public static Session getSession() {
		session =sessionFactory.getCurrentSession();
		return session;
	}
	public static void closeSession() {
		session.getTransaction().commit();
		session.close();
	}
	
	private static void  initTestDate() {
		
		getSession().getTransaction().begin();
		//һ�Զ��ϵ
		if(getSession().get(Staff.class, 1) == null) {
			//�������
			Staff testStaff = new Staff();
			testStaff.setNumber("A000000000");
			testStaff.setFirstName("chan");
			testStaff.setLastName("harmful");
			testStaff.setCountry("china");
			testStaff.setProvince("gaungdong");
			testStaff.setCity("guangzhou");
			testStaff.setZipCode("511400");
			testStaff.setEntryDate("20191201");

			Account testAccount = new Account("0000000000", "000000", "20191201", "�����ʺ�");
			
			Department director = (Department)getSession().get(Department.class, 5);
			
			Role root = (Role)getSession().get(Role.class, 4);
			
			testStaff.setAccount(testAccount);
			testStaff.setDepartment(director);
			testStaff.setRole(root);
			
			director.getAccounts().add(testAccount);
			
			testAccount.getRoles().add(root);
			
			getSession().save(testStaff);
			getSession().save(testAccount);
			
			
			getSession().getTransaction().commit();

		}
		
		Staff staff =  (Staff)getSession().get(Staff.class, 1);
		String s = staff.toString();
		LogUtil.Log("[Hibernate]:test staff :"+s);
	}
	
	private static void initCoreRelation() {
		getSession().getTransaction().begin();
		//�����������ݹ�ϵ    role <=> permission
		if(((Role)(getSession().get(Role.class, 1))).getPermissions().size() == 0) {
			Role normal = (Role)getSession().get(Role.class, 1);
			Role governor = (Role)getSession().get(Role.class, 2);
			Role administrator = (Role)getSession().get(Role.class, 3);
			Role root = (Role)getSession().get(Role.class, 4);
			
			Permission p5 = (Permission)getSession().get(Permission.class, 1);
			Permission p10 = (Permission)getSession().get(Permission.class, 2);
			Permission p20 = (Permission)getSession().get(Permission.class, 3);
			Permission p30 = (Permission)getSession().get(Permission.class, 4);
			Permission p40 = (Permission)getSession().get(Permission.class, 5);
			Permission p50 = (Permission)getSession().get(Permission.class, 6);
			Permission p60 = (Permission)getSession().get(Permission.class, 7);
			Permission p70 = (Permission)getSession().get(Permission.class, 8);
			Permission p80 = (Permission)getSession().get(Permission.class, 9);
			Permission p90 = (Permission)getSession().get(Permission.class, 10);
			Permission p100 = (Permission)getSession().get(Permission.class, 11);
			Permission p110 = (Permission)getSession().get(Permission.class, 12);
			Permission p120 = (Permission)getSession().get(Permission.class, 13);
		
			Node n1_2 = (Node)getSession().get(Node.class, 2);
			Node n1_3 = (Node)getSession().get(Node.class, 3);
			Node n2_1 = (Node)getSession().get(Node.class, 4);
			Node n2_2 = (Node)getSession().get(Node.class, 5);
			Node n3_1 = (Node)getSession().get(Node.class, 6);
			Node n3_2 = (Node)getSession().get(Node.class, 7);
			Node n3_3 = (Node)getSession().get(Node.class, 8);
			Node n3_4 = (Node)getSession().get(Node.class, 9);
			Node n3_5 = (Node)getSession().get(Node.class, 10);
			Node n3_6 = (Node)getSession().get(Node.class, 11);
			Node n3_7 = (Node)getSession().get(Node.class, 12);
			Node n3_8 = (Node)getSession().get(Node.class, 13);
			Node n3_9 = (Node)getSession().get(Node.class, 14);
			Node n3_10 = (Node)getSession().get(Node.class, 15);
			Node n3_11 = (Node)getSession().get(Node.class, 16);
			Node n3_12 = (Node)getSession().get(Node.class, 17);
			
			normal.getPermissions().add(p5);
			normal.getPermissions().add(p10);
			governor.getPermissions().add(p5);
			governor.getPermissions().add(p10);
			governor.getPermissions().add(p20);
			governor.getPermissions().add(p30);
			governor.getPermissions().add(p40);
			administrator.getPermissions().add(p5);
			administrator.getPermissions().add(p50);
			administrator.getPermissions().add(p60);
			administrator.getPermissions().add(p70);
			administrator.getPermissions().add(p80);
			root.getPermissions().add(p5);
			root.getPermissions().add(p90);
			root.getPermissions().add(p100);
			root.getPermissions().add(p110);
			root.getPermissions().add(p120);
			p5.getNodes().add(n1_2);
			p5.getNodes().add(n1_3);
			p5.getNodes().add(n2_1);
			p5.getNodes().add(n2_2);
			p10.getNodes().add(n3_1);
			p20.getNodes().add(n3_2);
			p30.getNodes().add(n3_3);
			p40.getNodes().add(n3_4);
			p50.getNodes().add(n3_5);
			p60.getNodes().add(n3_6);
			p70.getNodes().add(n3_7);
			p80.getNodes().add(n3_8);
			p90.getNodes().add(n3_9);
			p100.getNodes().add(n3_10);
			p110.getNodes().add(n3_11);
			p120.getNodes().add(n3_12);
			
		}
		getSession().getTransaction().commit();
	}
	
	private static void initCoreData() {
		getSession().getTransaction().begin();
		//���ݿ�д���������
		if(getSession().get(Department.class, 1) == null) {
			//������Ϣ
			Department d1 = new Department(Department.MARKER, "�г���");
			Department d2 = new Department(Department.PRODUCTION, "������");
			Department d3 = new Department(Department.DEVAELOPMENT, "�з���");
			Department d4 = new Department(Department.MANAGERMENT, "����");
			Department d5 = new Department(Department.DIRECTOR, "���»�");
			getSession().save(d1);
			getSession().save(d2);
			getSession().save(d3);
			getSession().save(d4);
			getSession().save(d5);
			//ְλ����
			Role r1 = new Role(Role.NORMAL, "��ͨԱ��");
			Role r2 = new Role(Role.GOVERNOR, "���ž���");
			Role r3 = new Role(Role.ADMINISTRATOR, "��̨����Ա");
			Role r4 = new Role(Role.ROOT, "��߹���Ա");
			getSession().save(r1);
			getSession().save(r2);
			getSession().save(r3);
			getSession().save(r4);
			//Ȩ������
			
			Permission p5 = new Permission(5,  "�����˳����鿴��Ϣ���޸���Ϣ");
			Permission p10 = new Permission(10,  "����鿴��ͨ�˺š�����");
			Permission p20 = new Permission(20,  "���������ͨ�˺�");
			Permission p30 = new Permission(30,  "�����޸���ͨ�˺ŵ�����");
			Permission p40 = new Permission(40,  "����ɾ����ͨ�˺�");
			Permission p50 = new Permission(50,  "����鿴�����˺š�����");
			Permission p60 = new Permission(60,  "�����������˺�");
			Permission p70 = new Permission(70,  "�����޸Ĺ����˺ŵ�����");
			Permission p80 = new Permission(80,  "����ɾ�������˺�");
			Permission p90 = new Permission(90,  "����鿴ȫ���˺š�����");
			Permission p100 = new Permission(100, "�������ȫ���˺�");
			Permission p110 = new Permission(110, "�����޸�ȫ���˺ŵ�����");
			Permission p120 = new Permission(120, "����ɾ��ȫ���˺�");
			
			getSession().save(p5);
			getSession().save(p10);
			getSession().save(p20);
			getSession().save(p30);
			getSession().save(p40);
			getSession().save(p50);
			getSession().save(p60);
			getSession().save(p70);
			getSession().save(p80);
			getSession().save(p90);
			getSession().save(p100);
			getSession().save(p110);
			getSession().save(p120);

			//��Դ����
			
			Node n1_1 = new Node(false, "/account/Home/home");
			Node n1_2 = new Node(true, "/account/Home/login");
			Node n1_3 = new Node(true, "/account/Home/deplan");
			Node n2_1 = new Node(true, "/account/UserProfile/getInfo");
			Node n2_2 = new Node(true, "/account/UserProfile/alterdInfo");
			Node n3_1 = new Node(true, "/account/AccountTable/getInterior");
			Node n3_2 = new Node(true, "/account/AccountTable/addInterior");
			Node n3_3 = new Node(true, "/account/AccountTable/alterInterior");
			Node n3_4 = new Node(true, "/account/AccountTable/removeInterior");
			Node n3_5 = new Node(true, "/account/AccountTable/getSuper");
			Node n3_6 = new Node(true, "/account/AccountTable/addSuper");
			Node n3_7 = new Node(true, "/account/AccountTable/alterSuper");
			Node n3_8 = new Node(true, "/account/AccountTable/removeSuper");
			Node n3_9 = new Node(true, "/account/AccountTable/getRoot");
			Node n3_10 = new Node(true, "/account/AccountTable/addRoot");
			Node n3_11 = new Node(true, "/account/AccountTable/alterRoot");
			Node n3_12 = new Node(true, "/account/AccountTable/removeRoot");
			getSession().save(n1_1);
			getSession().save(n1_2);
			getSession().save(n1_3);
			getSession().save(n2_1);
			getSession().save(n2_2);
			getSession().save(n3_1);
			getSession().save(n3_2);
			getSession().save(n3_3);
			getSession().save(n3_4);
			getSession().save(n3_5);
			getSession().save(n3_6);
			getSession().save(n3_7);
			getSession().save(n3_8);
			getSession().save(n3_9);
			getSession().save(n3_10);
			getSession().save(n3_11);
			getSession().save(n3_12);
		}
		getSession().getTransaction().commit();
	}

}
