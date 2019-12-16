package org.account.web.action;

import java.util.List;
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

	protected void flashUrl(Staff staff) throws Exception {
		List<Staff> holdStaffList = info.getHoldStaffList();    //添加测试用户
		List<Staff> notHoldStaffList = info.getNotHoldStaffList();
		String urlHeader = (String)session.get("urlHeader");
		session.put("test_hold_staffs", holdStaffList);
		session.put("test_not_hold_staffs", notHoldStaffList);
		session.put("display", "display:block");
		session.put("home_url", urlHeader+"/Home/home");
		session.put("user_url", urlHeader+"/User/getInfo?operator="+staff.getNumber());
		session.put("deplan_url", urlHeader+"/Home/deplan?operator="+staff.getNumber());
		//按角色修改页面连接
		if(staff.getRole().getName().equals(Role.ROOT)) {    //董事长
			session.put("table_url", urlHeader+"/Table/getRoot?operator="+staff.getNumber());	
			session.put("table_add", urlHeader+"/Table/addRoot?operator="+staff.getNumber());
			session.put("table_alter", urlHeader+"/Table/alterRoot?operator="+staff.getNumber());
			session.put("table_remove", urlHeader+"/Table/removeRoot?operator="+staff.getNumber());
		}else if(staff.getRole().getName().equals(Role.ADMINISTRATOR)) {    //账号管理员
			session.put("table_url", urlHeader+"/Table/getSuper?operator="+staff.getNumber());	
			session.put("table_add", urlHeader+"/Table/addSuper?operator="+staff.getNumber());
			session.put("table_alter", urlHeader+"/Table/alterSuper?operator="+staff.getNumber());
			session.put("table_remove", urlHeader+"/Table/removeSuper?operator="+staff.getNumber());
		}else if(staff.getRole().getName().equals(Role.GOVERNOR)) {    //部门经理
			session.put("table_url", urlHeader+"/Table/getInterior?operator="+staff.getNumber());	
			session.put("table_add", urlHeader+"/Table/addInterior?operator="+staff.getNumber());
			session.put("table_alter", urlHeader+"/Table/alterInterior?operator="+staff.getNumber());
			session.put("table_remove", urlHeader+"/Table/removeInterior?operator="+staff.getNumber());	
		}else if(staff.getRole().getName().equals(Role.NORMAL)) {    //普通员工
			session.put("table_url", urlHeader+"/Table/getInterior?operator="+staff.getNumber());	
			session.put("table_add", "#");
			session.put("table_alter", "#");
			session.put("table_remove", "#");
			session.put("display", "display:none");    //不显示按钮
		}
	}
	
}
