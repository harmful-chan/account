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
		session.put("home_url", (String)session.get("urlHeader")+"/Home/home");
		session.put("login_url", (String)session.get("urlHeader")+"/Home/login");
		session.put("user_url", "#");
		session.put("table_url", "#");
		session.put("deplan_url", "#");
		LoggerServer.console("Action: Home/home ������ҳ");
		if(!(boolean)session.get("isRedirect")) {
			LoggerServer.info("���ȵ�½��˾�ڲ��ṩ���ʺ�,���ڲ�����Ա������ʹ�ñ�ϵͳ");
		}
		
		
		return "home";
	}
	public String login() throws Exception {
		
		Staff staff = active.getCurrent();
		Account account = staff.getAccount();
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
		LoggerServer.console("Action: Home/login ��ӵ�ǰԱ��Ϊ��¼Ա��");
		active.addActive(active.getCurrent());    
		
		//������תҳ����Ϣ
		session.put("login_info", homeRequest);
		LoggerServer.success("��ӭԱ����¼�����ţ�"+staff.getNumber());
		flashUrl(staff);
		
		LoggerServer.console("Action: Home/login �����û���ҳ");
		return "welcome";
	}
	@SkipValidation
	public String deplan() {
		LoggerServer.console("Action: Home/deplan ��¼�û��Ƴ���ǰ�û�");
		active.removeActive(active.getCurrent());
		LoggerServer.info("���ţ�"+active.getCurrent().getNumber()+"�˳��ɹ�");
		LoggerServer.console("Action: Home/deplan ������ҳ");
		return "home";
	}
	
	@Override
	public HomeContext getModel() {
		return this.homeRequest;
	}
}
