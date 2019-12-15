package org.account.web.action;

import java.util.List;

import org.account.orm.bean.Account;
import org.account.orm.bean.Department;
import org.account.orm.bean.Role;
import org.account.orm.bean.Staff;
import org.account.orm.services.LoggerServer;
import org.account.web.bean.HomeContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends ActionBase implements ModelDriven<HomeContext>{
	private static final long serialVersionUID = 1L;
	private HomeContext homeRequest = new HomeContext();
	
	@SkipValidation
	public String home() throws Exception {
		List<Staff> holdStaffList = info.getHoldStaffList();    //添加测试用户
		List<Staff> notHoldStaffList = info.getNotHoldStaffList();
		session.put("test_hold_staffs", holdStaffList);
		session.put("test_not_hold_staffs", notHoldStaffList);
		session.put("user_url", "#");
		session.put("table_url", "#");
		session.put("home_url", "http://localhost:8080/account/Home/home");
		session.put("login_url", "http://localhost:8080/account/Home/login");
		session.put("deplan_url", "#");
		if(!(boolean)session.get("isRedirect")) {
			LoggerServer.info("请先登陆公司内部提供的帐号,非内部人人员不允许使用本系统");
		}
			
		
		
		return "home";
	}
	public String login() throws Exception {
		Staff staff = active.getCurrent();
		

		
		
		Account account = info.getHoldStaffAccount(staff.getNumber());
		if(!account.getNumber().equals(homeRequest.getAccountNumber())) {
			LoggerServer.warning("员工"+staff.getNumber()+"登录账号错误");
			return ERROR;
		}
		
		String correct = account.getPassword();
		String password = secret.decodePassword( homeRequest.getDeeppwd());
		if(!password.equals(correct)) {
			LoggerServer.warning("员工"+staff.getNumber()+"登录密码错误");
			return ERROR;
		}

		//设置当前用户登录状态
		active.addActive(active.getCurrent());    
		
		
		//设置跳转页面
		
		session.put("login_info", homeRequest);
		LoggerServer.success("欢迎员工登录，工号："+staff.getNumber());
		flashUrl(staff);

		return "welcome";
	}
	@SkipValidation
	public String deplan() {
		active.removeActive(active.getCurrent());
		LoggerServer.info("工号："+active.getCurrent().getNumber()+"退出成功");
		return "home";
	}
	
	@Override
	public HomeContext getModel() {
		return this.homeRequest;
	}
}
