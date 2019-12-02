package org.account.util;

import org.account.orm.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	static{
		System.out.println("��ȡ�����ļ�");
		Configuration cfg = new Configuration().configure();
		System.out.println("�����Ự����");
		sessionFactory = cfg.buildSessionFactory();
		System.out.println("����д���������");
		initCoreData();
		System.out.println("���ݽ�����������");
		initCoreRelation();
		System.out.println("����д�������������");
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
		System.out.println("test staff :"+s);
	}
	
	private static void initCoreRelation() {
		getSession().getTransaction().begin();
		//�����������ݹ�ϵ    role <=> permission
		if(((Role)(getSession().get(Role.class, 1))).getPermissions().size() == 0) {
			Role normal = (Role)getSession().get(Role.class, 1);
			Role governor = (Role)getSession().get(Role.class, 2);
			Role administrator = (Role)getSession().get(Role.class, 3);
			Role root = (Role)getSession().get(Role.class, 4);
			Permission p10 = (Permission)getSession().get(Permission.class, 1);
			Permission p20 = (Permission)getSession().get(Permission.class, 2);
			Permission p30 = (Permission)getSession().get(Permission.class, 3);
			Permission p40 = (Permission)getSession().get(Permission.class, 4);
			Permission p50 = (Permission)getSession().get(Permission.class, 5);
			Permission p60 = (Permission)getSession().get(Permission.class, 6);
			Permission p70 = (Permission)getSession().get(Permission.class, 7);
			Permission p80 = (Permission)getSession().get(Permission.class, 8);
			Permission p90 = (Permission)getSession().get(Permission.class, 9);
			Permission p100 = (Permission)getSession().get(Permission.class, 10);
			Permission p110 = (Permission)getSession().get(Permission.class, 11);
			Permission p120 = (Permission)getSession().get(Permission.class, 12);

			Node n2 = (Node)getSession().get(Node.class, 2);
			Node n3 = (Node)getSession().get(Node.class, 3);
			Node n4 = (Node)getSession().get(Node.class, 4);
			Node n5 = (Node)getSession().get(Node.class, 5);
			Node n6 = (Node)getSession().get(Node.class, 6);
			Node n7 = (Node)getSession().get(Node.class, 7);
			Node n8 = (Node)getSession().get(Node.class, 8);
			Node n9 = (Node)getSession().get(Node.class, 9);
			Node n10 = (Node)getSession().get(Node.class, 10);
			Node n11 = (Node)getSession().get(Node.class, 11);
			Node n12 = (Node)getSession().get(Node.class, 12);
			Node n13 = (Node)getSession().get(Node.class, 13);
			Node n14 = (Node)getSession().get(Node.class, 14);
			Node n15 = (Node)getSession().get(Node.class, 15);
			Node n16 = (Node)getSession().get(Node.class, 16);
			
			normal.getPermissions().add(p10);
			governor.getPermissions().add(p10);
			governor.getPermissions().add(p20);
			governor.getPermissions().add(p30);
			governor.getPermissions().add(p40);
			administrator.getPermissions().add(p50);
			administrator.getPermissions().add(p60);
			administrator.getPermissions().add(p70);
			administrator.getPermissions().add(p80);
			root.getPermissions().add(p90);
			root.getPermissions().add(p100);
			root.getPermissions().add(p110);
			root.getPermissions().add(p120);
			p10.getNodes().add(n2);
			p10.getNodes().add(n3);
			p10.getNodes().add(n4);
			p10.getNodes().add(n5);
			p20.getNodes().add(n6);
			p30.getNodes().add(n7);
			p40.getNodes().add(n8);
			p50.getNodes().add(n9);
			p60.getNodes().add(n10);
			p70.getNodes().add(n11);
			p80.getNodes().add(n12);
			p90.getNodes().add(n13);
			p100.getNodes().add(n14);
			p110.getNodes().add(n15);
			p120.getNodes().add(n16);
			
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
			Permission p1 = new Permission(10,  "����鿴��ͨ�˺š�����");
			Permission p2 = new Permission(20,  "���������ͨ�˺�");
			Permission p3 = new Permission(30,  "�����޸���ͨ�˺ŵ�����");
			Permission p4 = new Permission(40,  "����ɾ����ͨ�˺�");
			Permission p5 = new Permission(50,  "����鿴�����˺š�����");
			Permission p6 = new Permission(60,  "�����������˺�");
			Permission p7 = new Permission(70,  "�����޸Ĺ����˺ŵ�����");
			Permission p8 = new Permission(80,  "����ɾ�������˺�");
			Permission p9 = new Permission(90,  "����鿴ȫ���˺š�����");
			Permission p10 = new Permission(100, "�������ȫ���˺�");
			Permission p11 = new Permission(110, "�����޸�ȫ���˺ŵ�����");
			Permission p12 = new Permission(120, "����ɾ��ȫ���˺�");
			getSession().save(p1);
			getSession().save(p2);
			getSession().save(p3);
			getSession().save(p4);
			getSession().save(p5);
			getSession().save(p6);
			getSession().save(p7);
			getSession().save(p8);
			getSession().save(p9);
			getSession().save(p10);
			getSession().save(p11);
			getSession().save(p12);
			//��Դ����
			Node n1 = new Node(false, "http://localhost:8080/account/Home/login");
			Node n2 = new Node(true, "http://localhost:8080/account/Home/deplan");
			Node n3 = new Node(true, "http://localhost:8080/account/UserProfile/getInfo");
			Node n4 = new Node(true, "http://localhost:8080/account/UserProfile/alterdInfo");
			Node n5 = new Node(true, "http://localhost:8080/account/AccountTable/getInterior");
			Node n6 = new Node(true, "http://localhost:8080/account/AccountTable/addInterior");
			Node n7 = new Node(true, "http://localhost:8080/account/AccountTable/alterInterior");
			Node n8 = new Node(true, "http://localhost:8080/account/AccountTable/removeInterior");
			Node n9 = new Node(true, "http://localhost:8080/account/AccountTable/getSuper");
			Node n10 = new Node(true, "http://localhost:8080/account/AccountTable/addSuper");
			Node n11 = new Node(true, "http://localhost:8080/account/AccountTable/alterSuper");
			Node n12 = new Node(true, "http://localhost:8080/account/AccountTable/removeSuper");
			Node n13 = new Node(true, "http://localhost:8080/account/AccountTable/getRoot");
			Node n14 = new Node(true, "http://localhost:8080/account/AccountTable/addRoot");
			Node n15 = new Node(true, "http://localhost:8080/account/AccountTable/alterRoot");
			Node n16 = new Node(true, "http://localhost:8080/account/AccountTable/removeRoot");
			getSession().save(n1);
			getSession().save(n2);
			getSession().save(n3);
			getSession().save(n4);
			getSession().save(n5);
			getSession().save(n6);
			getSession().save(n7);
			getSession().save(n8);
			getSession().save(n9);
			getSession().save(n10);
			getSession().save(n11);
			getSession().save(n12);
			getSession().save(n13);
			getSession().save(n14);
			getSession().save(n15);
			getSession().save(n16);
		}
		getSession().getTransaction().commit();
	}


	
	
}
