package org.account.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.account.orm.model.*;
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
			LoggerServer.console("[Hibernate]:读取配置文件]");
			Configuration cfg = new Configuration().configure();
			
			LoggerServer.console("[Hibernate]:建立会话工厂");
			sessionFactory = cfg.buildSessionFactory();
			
			LoggerServer.console("[Hibernate]:数据写入核心数据");
			initCoreData();
			
			LoggerServer.console("[Hibernate]:数据写入测试数据数据");
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
	

	private static void bulidData(int id, String number, String zipData, String firsh, String second, String city, String entryDate, int departmentId, int roleId, String accountNumber, String password, String salf, String explain) {
		//添加数据
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
			
			
			Department department = (Department)getSession().get(Department.class, departmentId);
			Role role = (Role)getSession().get(Role.class, roleId);
			
			staff.setDepartment(department);
			staff.setRole(role);
			

			if(accountNumber !=null && password != null) {
				Account  account = new Account(accountNumber, password, salf, explain);
				staff.setAccount(account);	
				account.getDepartments().add(department);
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
			bulidData(1,  "A000010000", "511400",   "chan",      "harmful",    "guangzhou", "20191201", 5, 4, "0000000000", "000000", curr, "测试帐号");
			bulidData(2,  "A000010001", "19530902", "Georgi",    "Facello",    "M",         "19860626", 5, 4, "0000000001", "000001", curr, "测试帐号");
			bulidData(3,  "A000010002", "19640602", "Bezalel",   "Simmel",     "F",         "19851121", 4, 3, "0000000002", "000002", curr, "测试帐号");
			bulidData(4,  "A000010003", "19591203", "Parto",     "Bamford",    "M",         "19860828", 4, 2, "0000000003", "000003", curr, "测试帐号");
			bulidData(5,  "A000010004", "19540501", "Chirstian", "Koblick",    "M",         "19861201", 4, 2, null, null, null, null);
			bulidData(6,  "A000010005", "19550121", "Kyoichi",   "Maliniak",   "M",         "19890912", 4, 1, null, null, null, null);
			bulidData(7,  "A000010006", "19530420", "Anneke",    "Preusig",    "F",         "19890602", 3, 2, "0000000004", "000004", curr, "测试帐号");
			bulidData(8,  "A000010007", "19570523", "Tzvetan",   "Zielinski",  "F",         "19890210", 3, 2, null, null, null, null);
			bulidData(9,  "A000010008", "19580219", "Saniya",    "Kalloufi",   "M",         "19940915", 3, 2, null, null, null, null);
			bulidData(10, "A000010009", "19520419", "Sumant",    "Peac",       "F",         "19850218", 3, 2, null, null, null, null);
			bulidData(11, "A000010010", "19630601", "Duangkaew", "Piveteau",   "F",         "19890824", 3, 2, null, null, null, null);
			bulidData(12, "A000010011", "19531107", "Mary",      "Sluis",      "F",         "19900122", 3, 1, null, null, null, null);
			bulidData(13, "A000010012", "19601004", "Patricio",  "Bridgland",  "M",         "19921218", 3, 1, null, null, null, null);
			bulidData(14, "A000010013", "19630607", "Eberhardt", "Terkki",     "M",         "19851020", 3, 1, "0000000005", "000005", curr, "测试帐号");
			bulidData(15, "A000010014", "19560212", "Berni",     "Genin",      "M",         "19870311", 2, 3, null, null, null, null);
			bulidData(16, "A000010015", "19590819", "Guoxiang",  "Nooteboom",  "M",         "19870702", 2, 3, null, null, null, null);
			bulidData(17, "A000010016", "19610502", "Kazuhito",  "Cappelletti","M",         "19950127", 2, 2, null, null, null, null);
			bulidData(18, "A000010017", "19580706", "Cristinel", "Bouloucos",  "F",         "19930803", 2, 2, "0000000006", "000006", curr, "测试帐号");
			bulidData(19, "A000010018", "19540619", "Kazuhide",  "Peha",       "F",         "19870403", 2, 1, null, null, null, null);
			bulidData(20, "A000010019", "19530123", "Lillian",   "Haddadi",    "M",         "19990430", 2, 1, null, null, null, null);
			bulidData(21, "A000010020", "19521224", "Mayuko",    "Warwick",    "M",         "19910126", 2, 1, null, null, null, null);
			bulidData(22, "A000010021", "19600220", "Ramzi",     "Erde",       "M",         "19880210", 2, 1, null, null, null, null);
			bulidData(23, "A000010022", "19520708", "Shahaf",    "Famili",     "M",         "19950822", 2, 1, null, null, null, null);
			bulidData(24, "A000010023", "19530929", "Bojan",     "Montemayor", "F",         "19891217", 1, 3, null, null, null, null);
			bulidData(25, "A000010024", "19580905", "Suzette",   "Pettey",     "F",         "19970519", 1, 3, null, null, null, null);
			bulidData(26, "A000010025", "19581031", "Prasadram", "Heyers",     "M",         "19870817", 1, 2, null, null, null, null);
			bulidData(27, "A000010026", "19530403", "Yongqiao",  "Berztiss",   "M",         "19950320", 1, 2, null, null, null, null);
			bulidData(28, "A000010027", "19620710", "Divier",    "Reistad",    "F",         "19890707", 1, 1, null, null, null, null);
			bulidData(29, "A000010028", "19631126", "Domenick",  "Tempesti",   "M",         "19911022", 1, 1, null, null, null, null);
			bulidData(30, "A000010029", "19561213", "Otmar",     "Herbst",     "M",         "19851120", 1, 1, null, null, null, null);
			bulidData(31, "A000010030", "19580714", "Elvis",     "Demeyer",    "M",         "19940217", 1, 1, null, null, null, null);
			
			for (int i = 7; i < 50; i++) {
				bulidData(i, null, null, null, null, null, null, (i % 5)+1, 0, 
						"00000000"+ ((i<10)? "0"+i: i), 
						"0000"+ ((i<10)? "0"+i: i), 
						curr, "测试帐号");	
			}
		}
		getSession().getTransaction().commit();		
	}
	
	private static void initCoreData() {
		getSession().getTransaction().begin();
		//数据库写入核心数据
		if(getSession().get(Department.class, 1) == null) {
			//部门信息
			getSession().save(new Department(Department.MARKER, "市场部"));
			getSession().save(new Department(Department.PRODUCTION, "生产部"));
			getSession().save(new Department(Department.DEVAELOPMENT, "研发部"));
			getSession().save(new Department(Department.MANAGERMENT, "管理部"));
			getSession().save(new Department(Department.DIRECTOR, "董事会"));

			//职位数据
			Role normal        = (new Role(Role.NORMAL, "普通员工"));
			Role governor      = (new Role(Role.GOVERNOR, "部门经理"));
			Role administrator = (new Role(Role.ADMINISTRATOR, "后台管理员"));
			Role root          = (new Role(Role.ROOT, "最高管理员"));

			//权限数据
			
			Permission p5   = (new Permission(5,   "允许退出，查看信息、修改信息"));
			Permission p10  = (new Permission(10,  "允许查看普通账号、密码"));
			Permission p20  = (new Permission(20,  "允许插入普通账号"));
			Permission p30  = (new Permission(30,  "允许修改普通账号的密码"));
			Permission p40  = (new Permission(40,  "允许删除普通账号"));
			Permission p50  = (new Permission(50,  "允许查看管理账号、密码"));
			Permission p60  = (new Permission(60,  "允许插入管理账号"));
			Permission p70  = (new Permission(70,  "允许修改管理账号的密码"));
			Permission p80  = (new Permission(80,  "允许删除管理账号"));
			Permission p90  = (new Permission(90,  "允许查看全部账号、密码"));
			Permission p100 = (new Permission(100, "允许插入全部账号"));
			Permission p110 = (new Permission(110, "允许修改全部账号的密码"));
			Permission p120 = (new Permission(120, "允许删除全部账号"));

			//资源数据
			
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
