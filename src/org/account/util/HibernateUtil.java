package org.account.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.account.orm.bean.*;
import org.account.orm.services.AssignedServer;
import org.account.orm.services.LoggerServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private static boolean isInit = false;
	
	public static Session getSession() {
		session =sessionFactory.getCurrentSession();
		return session;
	}
	public static void closeSession() {
		//session.close();
	}
	
	public static void init() {
		if(!isInit) {
			LoggerServer.console("Hibernate:��ȡ�����ļ�");
			Configuration cfg = new Configuration().configure();
			
			LoggerServer.console("Hibernate:�����Ự����");
			sessionFactory = cfg.buildSessionFactory();
			
			LoggerServer.console("Hibernate:����д���������");
			initCoreData();
			
			LoggerServer.console("Hibernate:����д�������������");
			initTestDate();
			
			isInit= true;
		}

	}
	
	public static Object get(Class<? extends Object> arg0, int arg1) {
		Object ret = null;
		try {
			getSession().beginTransaction();
			ret = getSession().get(arg0, arg1);
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return null;
		}finally {
			getSession().close();
		}
		return ret;
	}
	
	public static int save(Object arg0) {
		try {
			getSession().beginTransaction();
			getSession().save(arg0);
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return -1;
		}finally {
			getSession().close();
		}
		return 1;
	}
	
	public static int update(Object arg0) {
		try {
			getSession().beginTransaction();
			getSession().update(arg0);
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return -1;
		}finally {
			getSession().close();
		}
		return 1;
	}
	
	
	
	public static int delete(Object arg0) {
		try {
			getSession().beginTransaction();
			getSession().delete(arg0);
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return -1;
		}finally {
			getSession().close();
		}
		return 1;
	}
	
	public static Object queryOnly(String sql) {
		Object ret = null;
		try {
			getSession().beginTransaction();
			ret = getSession().createQuery(sql).uniqueResult();
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return null;
		}finally {
			getSession().close();
		}
		return ret;
	}
	
	public static Object queryList(String sql) {
		Object ret = null;
		try {
			getSession().beginTransaction();
			ret = getSession().createQuery(sql).list();
			getSession().getTransaction().commit();
		}catch(Exception e) {
			return null;
		}finally {
			getSession().close();
		}
		return ret;
	}
	

	private static void bulidData(int id, String number, String zipData, String firsh, String second, String city, String entryDate, String email, int departmentId, int roleId, String accountNumber, String password, String salf, String explain) {
		//�������
		if(number != null) {
			Staff staff = new Staff();
			staff.setNumber(number);
			staff.setFirstName(firsh);
			staff.setLastName(second);
			staff.setCountry("china");
			staff.setProvince(city);
			staff.setCity("guangzhou");
			staff.setZipCode(zipData);
			staff.setEntryDate(entryDate);
			staff.setEmail(email);
			
			
			Department department = (Department)getSession().get(Department.class, departmentId);
			Role role = (Role)getSession().get(Role.class, roleId);
			
			staff.setDepartment(department);
			staff.setRole(role);
			

			if(accountNumber !=null && password != null) {
				Account  account = new Account(accountNumber, password, salf, explain);
				staff.setAccount(account);	
				getSession().save(account);	
			}
			
			getSession().save(staff);		
		}else {
			Account a = new Account(accountNumber, password, salf, explain);
			Department d = (Department)getSession().get(Department.class, departmentId);
			a.getDepartments().add(d);
			getSession().save(a);	
		}

	}

	private static void initTestDate() {
		getSession().getTransaction().begin();
		boolean flag = getSession().get(Staff.class, 1) == null;
		flag &=  getSession().get(Account.class, 1) == null;
		if(flag) {
			String curr = (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date());
			bulidData(1,  "A000010000", "511400", "chan",      "harmful",    "guangzhou", "2019-12-01", "1148706823@qq.com", 5, 4, "0000000000", "000000", curr, "�����ʺ�");
			bulidData(2,  "A000010001", "530902", "Georgi",    "Facello",    "M",         "1986-06-26", "1148706823@qq.com", 5, 4, "0000000001", "000001", curr, "�����ʺ�");
			bulidData(3,  "A000010002", "640602", "Bezalel",   "Simmel",     "F",         "1985-11-21", "1148706823@qq.com", 4, 3, "0000000002", "000002", curr, "�����ʺ�");
			bulidData(4,  "A000010003", "591203", "Parto",     "Bamford",    "M",         "1986-08-28", "1148706823@qq.com", 4, 3, "0000000003", "000003", curr, "�����ʺ�");
			bulidData(5,  "A000010004", "540501", "Chirstian", "Koblick",    "M",         "1986-12-01", "1148706823@qq.com", 4, 2, "0000000004", "000004", curr, "�����ʺ�");
			bulidData(6,  "A000010005", "550121", "Kyoichi",   "Maliniak",   "M",         "1989-09-12", "1148706823@qq.com", 4, 1, "0000000005", "000005", curr, "�����ʺ�");
			bulidData(7,  "A000010006", "530420", "Anneke",    "Preusig",    "F",         "1989-06-02", "1148706823@qq.com", 3, 2, "0000000006", "000006", curr, "�����ʺ�");
			bulidData(8,  "A000010007", "570523", "Tzvetan",   "Zielinski",  "F",         "1989-02-10", "1148706823@qq.com", 3, 1, null, null, null, null);
			bulidData(9,  "A000010008", "580219", "Saniya",    "Kalloufi",   "M",         "1994-09-15", "1148706823@qq.com", 2, 2, null, null, null, null);
			bulidData(10, "A000010009", "520419", "Sumant",    "Peac",       "F",         "1985-02-18", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(11, "A000010010", "630601", "Duangkaew", "Piveteau",   "F",         "1989-08-24", "1148706823@qq.com", 1, 2, null, null, null, null);
			bulidData(12, "A000010011", "531107", "Mary",      "Sluis",      "F",         "1990-01-22", "1148706823@qq.com", 1, 1, null, null, null, null);
			bulidData(13, "A000010012", "601004", "Patricio",  "Bridgland",  "M",         "1992-12-18", "1148706823@qq.com", 3, 1, null, null, null, null);
			bulidData(14, "A000010013", "630607", "Eberhardt", "Terkki",     "M",         "1985-10-20", "1148706823@qq.com", 3, 1, null, null, null, null);
			bulidData(15, "A000010014", "560212", "Berni",     "Genin",      "M",         "1987-03-11", "1148706823@qq.com", 2, 2, null, null, null, null);
			bulidData(16, "A000010015", "590819", "Guoxiang",  "Nooteboom",  "M",         "1987-07-02", "1148706823@qq.com", 2, 2, null, null, null, null);
			bulidData(17, "A000010016", "610502", "Kazuhito",  "Cappelletti","M",         "1995-01-27", "1148706823@qq.com", 2, 2, null, null, null, null);
			bulidData(18, "A000010017", "580706", "Cristinel", "Bouloucos",  "F",         "1993-08-03", "1148706823@qq.com", 2, 2, null, null, null, null);
			bulidData(19, "A000010018", "540619", "Kazuhide",  "Peha",       "F",         "1987-04-03", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(20, "A000010019", "530123", "Lillian",   "Haddadi",    "M",         "1999-04-30", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(21, "A000010020", "521224", "Mayuko",    "Warwick",    "M",         "1991-01-26", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(22, "A000010021", "600220", "Ramzi",     "Erde",       "M",         "1988-02-10", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(23, "A000010022", "520708", "Shahaf",    "Famili",     "M",         "1995-08-22", "1148706823@qq.com", 2, 1, null, null, null, null);
			bulidData(24, "A000010023", "530929", "Bojan",     "Montemayor", "F",         "1989-12-17", "1148706823@qq.com", 1, 2, null, null, null, null);
			bulidData(25, "A000010024", "580905", "Suzette",   "Pettey",     "F",         "1997-05-19", "1148706823@qq.com", 1, 2, null, null, null, null);
			bulidData(26, "A000010025", "581031", "Prasadram", "Heyers",     "M",         "1987-08-17", "1148706823@qq.com", 1, 2, null, null, null, null);
			bulidData(27, "A000010026", "530403", "Yongqiao",  "Berztiss",   "M",         "1995-03-20", "1148706823@qq.com", 1, 2, null, null, null, null);
			bulidData(28, "A000010027", "620710", "Divier",    "Reistad",    "F",         "1989-07-07", "1148706823@qq.com", 1, 1, null, null, null, null);
			bulidData(29, "A000010028", "631126", "Domenick",  "Tempesti",   "M",         "1991-10-22", "1148706823@qq.com", 1, 1, null, null, null, null);
			bulidData(30, "A000010029", "561213", "Otmar",     "Herbst",     "M",         "1985-11-20", "1148706823@qq.com", 1, 1, null, null, null, null);
			bulidData(31, "A000010030", "580714", "Elvis",     "Demeyer",    "M",         "1994-02-17", "1148706823@qq.com", 1, 1, null, null, null, null);
			
			for (int i = 0; i < 10; i++) {
				bulidData(i, null, null, null, null, null, null, null, (i%5)+1, 0, RandomStringUtils.random(8, false, true),  RandomStringUtils.random(13, true, true), curr, "�����(http://emuch.net)");
				bulidData(i, null, null, null, null, null, null, null, (i%5)+1, 0, RandomStringUtils.random(9, false, true),  RandomStringUtils.random(9, true, true), curr, "(http://gege521.com��");	
				bulidData(i, null, null, null, null, null, null, null, (i%5)+1, 0, RandomStringUtils.random(10, false, true),  RandomStringUtils.random(12, true, true), curr, "������Ȼ��ѧ����(http://www.nsfc.gov.cn)");	
				bulidData(i, null, null, null, null, null, null, null, (i%5)+1, 0, RandomStringUtils.random(11, false, true),  RandomStringUtils.random(11, true, true), curr, "(http://211.68.23.76/a.asp)");	
				bulidData(i, null, null, null, null, null, null, null, (i%5)+1, 0, RandomStringUtils.random(12, false, true),  RandomStringUtils.random(8, true, true), curr, "�������԰��http://www.newhua.com/��");	
			}
		}
		getSession().getTransaction().commit();		
	}
	
	private static void initCoreData() {
		getSession().getTransaction().begin();
		//���ݿ�д���������
		if(getSession().get(Department.class, 1) == null) {
			//������Ϣ
			getSession().save(new Department(Department.MARKER, "�г���"));
			getSession().save(new Department(Department.PRODUCTION, "������"));
			getSession().save(new Department(Department.DEVAELOPMENT, "�з���"));
			getSession().save(new Department(Department.MANAGERMENT, "����"));
			getSession().save(new Department(Department.DIRECTOR, "���»�"));

			//ְλ����
			Role normal        = (new Role(Role.NORMAL, "��ͨԱ��"));
			Role governor      = (new Role(Role.GOVERNOR, "���ž���"));
			Role administrator = (new Role(Role.ADMINISTRATOR, "�˺Ź���Ա"));
			Role root          = (new Role(Role.ROOT, "��������Ա"));

			//Ȩ������
			
			Permission p5   = (new Permission(5,   "�����˳����鿴��Ϣ���޸���Ϣ"));
			Permission p10  = (new Permission(10,  "����鿴��ͨ�˺š�����"));
			Permission p20  = (new Permission(20,  "���������ͨ�˺�"));
			Permission p30  = (new Permission(30,  "�����޸���ͨ�˺ŵ�����"));
			Permission p40  = (new Permission(40,  "����ɾ����ͨ�˺�"));
			Permission p50  = (new Permission(50,  "����鿴�����˺š�����"));
			Permission p60  = (new Permission(60,  "�����������˺�"));
			Permission p70  = (new Permission(70,  "�����޸Ĺ����˺ŵ�����"));
			Permission p80  = (new Permission(80,  "����ɾ�������˺�"));
			Permission p90  = (new Permission(90,  "����鿴ȫ���˺š�����"));
			Permission p100 = (new Permission(100, "�������ȫ���˺�"));
			Permission p110 = (new Permission(110, "�����޸�ȫ���˺ŵ�����"));
			Permission p120 = (new Permission(120, "����ɾ��ȫ���˺�"));

			//��Դ����
			
			getSession().save(new Node("home", false, "/account/Home/home"));
			Node login  = (new Node("login", true, "/account/Home/login"));
			Node deplan  = (new Node("deplan", true, "/account/Home/deplan"));
			Node getInfo  = (new Node("getInfo", true, "/account/User/getInfo"));
			Node alterdInfo  = (new Node("alterdInfo", true, "/account/User/alterdInfo"));
			Node getInterior  = (new Node("getInterior", true, "/account/Table/getInterior"));
			Node addInterior  = (new Node("addInterior", true, "/account/Table/addInterior"));
			Node alterInterior  = (new Node("alterInterior", true, "/account/Table/alterInterior"));
			Node removeInterior  = (new Node("removeInterior", true, "/account/Table/removeInterior"));
			Node getSuper  = (new Node("getSuper", true, "/account/Table/getSuper"));
			Node addSuper  = (new Node("addSuper", true, "/account/Table/addSuper"));
			Node alterSuper  = (new Node("alterSuper", true, "/account/Table/alterSuper"));
			Node removeSuper  = (new Node("removeSuper", true, "/account/Table/removeSuper"));
			Node getRoot  = (new Node("getRoot", true, "/account/Table/getRoot"));
			Node addRoot = (new Node("addRoot", true, "/account/Table/addRoot"));
			Node alterRoot = (new Node("alterRoot", true, "/account/Table/alterRoot"));
			Node removeRoot = (new Node("removeRoot", true, "/account/Table/removeRoot"));
			
			AssignedServer assigned = new AssignedServer();
			assigned.addAssign(null, p5, login);
			assigned.addAssign(null, p5, deplan);

			assigned.addAssign(null, p5, getInfo);
			assigned.addAssign(null, p5, alterdInfo);
			
			assigned.addAssign(null, p10, getInterior);
			assigned.addAssign(null, p20, addInterior);
			assigned.addAssign(null, p30, alterInterior);
			assigned.addAssign(null, p40, removeInterior);
			
			assigned.addAssign(null, p50, getSuper);
			assigned.addAssign(null, p60, addSuper);
			assigned.addAssign(null, p70, alterSuper);
			assigned.addAssign(null, p80, removeSuper);
			
			assigned.addAssign(null, p90,  getRoot);
			assigned.addAssign(null, p100, addRoot);
			assigned.addAssign(null, p110, alterRoot);
			assigned.addAssign(null, p120, removeRoot);
			
			
			assigned.addAssign(normal, p5, null);
			assigned.addAssign(normal, p10, null);
			
			assigned.addAssign(governor, p5, null);
			assigned.addAssign(governor, p10, null);
			assigned.addAssign(governor, p20, null);
			assigned.addAssign(governor, p30, null);
			assigned.addAssign(governor, p40, null);
			
			assigned.addAssign(administrator, p5, null);
			assigned.addAssign(administrator, p50, null);
			assigned.addAssign(administrator, p60, null);
			assigned.addAssign(administrator, p70, null);
			assigned.addAssign(administrator, p80, null);
			
			
			assigned.addAssign(root, p5,  null);
			assigned.addAssign(root, p90,  null);
			assigned.addAssign(root, p100, null);
			assigned.addAssign(root, p110, null);
			assigned.addAssign(root, p120, null);
			
			getSession().save(login);
			getSession().save(deplan);
			getSession().save(getInfo);
			getSession().save(alterdInfo);
			getSession().save(getInterior);
			getSession().save(addInterior);
			getSession().save(alterInterior);
			getSession().save(removeInterior);
			getSession().save(getSuper);
			getSession().save(addSuper);
			getSession().save(alterSuper);
			getSession().save(removeSuper);
			getSession().save(getRoot);
			getSession().save(addRoot);
			getSession().save(alterRoot);
			getSession().save(removeRoot);
			
			
			
			getSession().save(p5  );
			getSession().save(p10 );
			getSession().save(p20 );
			getSession().save(p30 );
			getSession().save(p40 );
			getSession().save(p50 );
			getSession().save(p60 );
			getSession().save(p70 );
			getSession().save(p80 );
			getSession().save(p90 );
			getSession().save(p100);
			getSession().save(p110);
			getSession().save(p120);
			
			
			getSession().save(normal        );
			getSession().save(governor      );
			getSession().save(administrator );
			getSession().save(root          );
			
		}
		getSession().getTransaction().commit();
	}

}
