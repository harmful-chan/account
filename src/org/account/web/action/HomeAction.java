package org.account.web.action;

import org.account.orm.model.Department;
import org.account.orm.model.Role;
import org.account.web.viewmodel.Home;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends ActionBase implements ModelDriven<Home>{
	private static final long serialVersionUID = 1L;
	private Home home = new Home();
	
	public String home() {
		session.put("user", "#");
		session.put("table", "#");
		session.put("home_url", "http://localhost:8080/account/Home/home");
		session.put("login_url", "http://localhost:8080/account/Home/login");
		session.put("deplan_url", "#");
		session.put("msg", new String[] {INFO, "请先登陆公司内部提供的帐号,非内部人人员不允许使用本系统"});
		return SUCCESS;
	}
	public String login() {
		
		String deeppwd = home.getDeeppwd();
		String deep = arbitrate.decode(deeppwd);
		String salf = deep.substring(deep.length()-8, deep.length());
		String password = deep.replaceAll(salf, "");
		String dst = arbitrate.getAccountPassword(home.getAccount());
		
		if(!password.equals(dst)) {
			session.put("login_url", "http://localhost:8080/account/Home/login");
			session.put("msg", new String[] {DANGER, "密码不正确"});
			return SUCCESS;
		}else {
			arbitrate.addActiveStaff(home.getNumber());
			String department = arbitrate.getCurrentActiveStaff().getDepartment();
			String role = arbitrate.getCurrentActiveStaff().getRole();
			String number = arbitrate.getCurrentActiveStaff().getNumber();
			//管理部门管理员以上
			if(department.equals(Department.MANAGERMENT) && (role.equals(Role.ADMINISTRATOR) || role.equals(Role.ROOT))) {
				session.put("table", "http://localhost:8080/account/AccountTable/getSuper?number="+number);	
				session.put("table_add", "http://localhost:8080/account/AccountTable/addSuper");
				session.put("table_alter", "http://localhost:8080/account/AccountTable/alterSuper");
				session.put("table_remove", "http://localhost:8080/account/AccountTable/removeSuper");
			}else if(department.equals(Department.DIRECTOR)) {
				session.put("table", "http://localhost:8080/account/AccountTable/getRoot?number="+number);	
				session.put("table_add", "http://localhost:8080/account/AccountTable/addRoot");
				session.put("table_alter", "http://localhost:8080/account/AccountTable/alterRoot");
				session.put("table_remove", "http://localhost:8080/account/AccountTable/removeRoot");
			}else {
				session.put("table", "http://localhost:8080/account/AccountTable/getInterior?number="+number);	
				session.put("table_add", "http://localhost:8080/account/AccountTable/addInterior");
				session.put("table_alter", "http://localhost:8080/account/AccountTable/alterInterior");
				session.put("table_remove", "http://localhost:8080/account/AccountTable/removeInterior");	
				if(role.equals(Role.NORMAL)){	
					session.put("table_add", "#");
					session.put("table_alter", "#");
					session.put("table_remove", "#");	
				}else if(role.equals(Role.GOVERNOR)) {
					session.put("table_alter", "#");
					session.put("table_remove", "#");
				}else if(role.equals(Role.ADMINISTRATOR)) {
					session.put("table_remove", "#");
				}
			}
			
			session.put("user", "http://localhost:8080/account/UserProfile/getInfo?number="+number);
			session.put("deplan_url", "http://localhost:8080/account/Home/deplan?number="+number);
			session.put("login_info", home);
			session.put("msg", new String[] {SUCESS, "欢迎"+number});
		}
		return "welcome";
	}
	
	public String deplan() {
		arbitrate.removeActiveStaff(home.getNumber());
		session.put("msg", new String[] {SUCESS, "工号"+arbitrate.getCurrentActiveStaff().getNumber()+"退出成功"});
		return SUCCESS;
	}
	@Override
	public Home getModel() {
		return this.home;
	}
}
