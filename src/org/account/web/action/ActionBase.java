package org.account.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.bean.Role;
import org.account.orm.bean.Staff;
import org.account.orm.services.ActiveServer;
import org.account.orm.services.SecretServer;
import org.account.orm.services.InfoServer;

import com.opensymphony.xwork2.ActionSupport;

public class ActionBase extends ActionSupport implements IActionable{


	
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> session;
	protected ActiveServer<Staff> active;
	protected SecretServer secret;
	protected InfoServer info;
	protected HttpServletRequest request;
	@Override
	public void setSessionServer(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setActiveServer(ActiveServer<Staff> active) {
		this.active = active;
	}


	@Override
	public void setSecretServer(SecretServer secret) {
		this.secret = secret;
	}

	@Override
	public void setInfoServer(InfoServer info) {
		this.info = info;
	}

	@Override
	public void setRequestServer(HttpServletRequest request) {
		this.request = request;
	}

	protected void flashUrl(Staff staff) {
		session.put("display", "display:block");
		session.put("user_url", "http://localhost:8080/account/User/getInfo?operator="+staff.getNumber());
		session.put("deplan_url", "http://localhost:8080/account/Home/deplan?operator="+staff.getNumber());
		//按角色修改页面连接
		if(staff.getRole().getName().equals(Role.ROOT)) {    //董事长
			session.put("table_url", "http://localhost:8080/account/Table/getRoot?operator="+staff.getNumber());	
			session.put("table_add", "http://localhost:8080/account/Table/addRoot?operator="+staff.getNumber());
			session.put("table_alter", "http://localhost:8080/account/Table/alterRoot?operator="+staff.getNumber());
			session.put("table_remove", "http://localhost:8080/account/Table/removeRoot?operator="+staff.getNumber());
		}else if(staff.getRole().getName().equals(Role.ADMINISTRATOR)) {    //账号管理员
			session.put("table_url", "http://localhost:8080/account/Table/getSuper?operator="+staff.getNumber());	
			session.put("table_add", "http://localhost:8080/account/Table/addSuper?operator="+staff.getNumber());
			session.put("table_alter", "http://localhost:8080/account/Table/alterSuper?operator="+staff.getNumber());
			session.put("table_remove", "http://localhost:8080/account/Table/removeSuper?operator="+staff.getNumber());
		}else if(staff.getRole().getName().equals(Role.GOVERNOR)) {    //部门经理
			session.put("table_url", "http://localhost:8080/account/Table/getInterior?operator="+staff.getNumber());	
			session.put("table_add", "http://localhost:8080/account/Table/addInterior?operator="+staff.getNumber());
			session.put("table_alter", "http://localhost:8080/account/Table/alterInterior?operator="+staff.getNumber());
			session.put("table_remove", "http://localhost:8080/account/Table/removeInterior?operator="+staff.getNumber());	
		}else if(staff.getRole().getName().equals(Role.NORMAL)) {    //普通员工
			session.put("table_url", "http://localhost:8080/account/Table/getInterior?operator="+staff.getNumber());	
			session.put("table_add", "#");
			session.put("table_alter", "#");
			session.put("table_remove", "#");
			session.put("display", "display:none");    //不显示按钮
		}
	}
	
}
