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
		List<Staff> holdStaffList = info.getHoldStaffList();    //��Ӳ����û�
		List<Staff> notHoldStaffList = info.getNotHoldStaffList();
		session.put("test_hold_staffs", holdStaffList);
		session.put("test_not_hold_staffs", notHoldStaffList);
		session.put("user_url", "#");
		session.put("table_url", "#");
		session.put("home_url", "http://localhost:8080/account/Home/home");
		session.put("login_url", "http://localhost:8080/account/Home/login");
		session.put("deplan_url", "#");
		if(!(boolean)session.get("isRedirect")) {
			LoggerServer.info("���ȵ�½��˾�ڲ��ṩ���ʺ�,���ڲ�����Ա������ʹ�ñ�ϵͳ");
		}
			
		
		
		return "home";
	}
	public String login() throws Exception {
		Staff staff = active.getCurrent();
		

		
		
		Account account = info.getHoldStaffAccount(staff.getNumber());
		if(!account.getNumber().equals(homeRequest.getAccountNumber())) {
			LoggerServer.warning("Ա��"+staff.getNumber()+"��¼�˺Ŵ���");
			return ERROR;
		}
		
		String correct = account.getPassword();
		String password = secret.decodePassword( homeRequest.getDeeppwd());
		if(!password.equals(correct)) {
			LoggerServer.warning("Ա��"+staff.getNumber()+"��¼�������");
			return ERROR;
		}

		//���õ�ǰ�û���¼״̬
		active.addActive(active.getCurrent());    
		
		
		//������תҳ��
		
		session.put("login_info", homeRequest);
		LoggerServer.success("��ӭԱ����¼�����ţ�"+staff.getNumber());
		flashUrl(staff);

		return "welcome";
	}
	@SkipValidation
	public String deplan() {
		active.removeActive(active.getCurrent());
		LoggerServer.info("���ţ�"+active.getCurrent().getNumber()+"�˳��ɹ�");
		return "home";
	}
	
	@Override
	public HomeContext getModel() {
		return this.homeRequest;
	}
}
