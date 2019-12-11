package org.account.web.action;

import org.account.orm.model.Department;
import org.account.orm.model.Role;
import org.account.orm.model.Staff;
import org.account.orm.services.LoggerServer;
import org.account.web.model.HomeContext;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends ActionBase implements ModelDriven<HomeContext>{
	private static final long serialVersionUID = 1L;
	private HomeContext homeRequest = new HomeContext();
	
	public String home() {
		session.put("user_url", "#");
		session.put("table_url", "#");
		session.put("home_url", "http://localhost:8080/account/Home/home");
		session.put("login_url", "http://localhost:8080/account/Home/login");
		session.put("deplan_url", "#");
		LoggerServer.info("���ȵ�½��˾�ڲ��ṩ���ʺ�,���ڲ�����Ա������ʹ�ñ�ϵͳ");
		return SUCCESS;
	}
	public String login() {
		
		String deeppwd = homeRequest.getDeeppwd();
		String deep = encryption.decode(deeppwd);
		String salf = deep.substring(deep.length()-17, deep.length());
		String password = deep.replaceAll(salf, "");
		String dst = secret.getAccountPassword(homeRequest.getAccountNumber());
		
		if(!password.equals(dst)) {
			return ERROR;
		}else {
			active.addActive(active.getCurrent());
			Role role = staffInfo.getRole(active.getCurrent());
			Staff staff = staffInfo.getStaff(active.getCurrent());
			//�����Ź���Ա����
			if(role.getName().equals(Role.ROOT)) {    //���³�
				session.put("table_url", "http://localhost:8080/account/Table/getRoot?operator="+staff.getNumber());	
				session.put("table_add", "http://localhost:8080/account/Table/addRoot?operator="+staff.getNumber());
				session.put("table_alter", "http://localhost:8080/account/Table/alterRoot?operator="+staff.getNumber());
				session.put("table_remove", "http://localhost:8080/account/Table/removeRoot?operator="+staff.getNumber());
			}else if(role.getName().equals(Role.ADMINISTRATOR)) {
				session.put("table_url", "http://localhost:8080/account/Table/getSuper?operator="+staff.getNumber());	
				session.put("table_add", "http://localhost:8080/account/Table/addSuper?operator="+staff.getNumber());
				session.put("table_alter", "http://localhost:8080/account/Table/alterSuper?operator="+staff.getNumber());
				session.put("table_remove", "http://localhost:8080/account/Table/removeSuper?operator="+staff.getNumber());
			}else if(role.getName().equals(Role.GOVERNOR)) {
				session.put("table_url", "http://localhost:8080/account/Table/getInterior?operator="+staff.getNumber());	
				session.put("table_add", "http://localhost:8080/account/Table/addInterior?operator="+staff.getNumber());
				session.put("table_alter", "http://localhost:8080/account/Table/alterInterior?operator="+staff.getNumber());
				session.put("table_remove", "http://localhost:8080/account/Table/removeInterior?operator="+staff.getNumber());	
			}else if(role.getName().equals(Role.NORMAL)) {
				session.put("table_url", "http://localhost:8080/account/Table/getInterior?operator="+staff.getNumber());	
				session.put("table_add", "#");
				session.put("table_alter", "#");
				session.put("table_remove", "#");
			}
			session.put("login_info", homeRequest);
			session.put("user_url", "http://localhost:8080/account/User/getInfo?operator="+staff.getNumber());
			session.put("deplan_url", "http://localhost:8080/account/Home/deplan?operator="+staff.getNumber());
			LoggerServer.success("��ӭԱ����¼�����ţ�"+staff.getNumber());
		}
		return "welcome";
	}
	
	public String deplan() {
		active.removeActive(homeRequest.getOperator());
		Staff staff = staffInfo.getStaff(active.getCurrent());
		LoggerServer.success("���ţ�"+staff.getNumber()+"�˳��ɹ�");
		return SUCCESS;
	}
	@Override
	public HomeContext getModel() {
		return this.homeRequest;
	}
}
