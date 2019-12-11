package org.account.web.interceptor;

import java.util.List;

import org.account.orm.model.Node;
import org.account.orm.model.Staff;
import org.account.orm.services.*;
import org.account.orm.services.StaffInfoServer;
import org.account.util.HibernateUtil;
import org.account.util.JDBCUtil;
import org.account.web.action.IActionable;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class IdentityInterceptor  extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//��ʼ��[HibernateUtil]

		HibernateUtil.init();
		

		
		//��ʼ������
		ResourceServer resource = new ResourceServer();
		StaffInfoServer staffInfo = new StaffInfoServer();
		ActiveServer<String> active = new ActiveServer<String>();
		EncryptedServer encryption = new EncryptedServer();
		SecretServer secret = new SecretServer();
		String url = ServletActionContext.getRequest().getRequestURI();

		//ע�����
		Object action = invocation.getAction();
		if(action instanceof IActionable) {		
			((IActionable) action).setSessionServer(ServletActionContext.getContext().getSession());
			((IActionable) action).setActiveServer(active);
			((IActionable) action).setSecretServer(secret);
			((IActionable) action).setEncryptedServer(encryption);
			((IActionable) action).setStaffInfoServer(staffInfo);
		}
		
		//������Դ
		boolean isPublic =  resource.isPublicResource(url);
		if(isPublic) {
			return invocation.invoke();
		}else {
			//˽����Դ
			boolean isPrivate = resource.isPrivateResource(url);
			if(!isPrivate) {
				LoggerServer.danger("������Դ��Ч");
				return Action.ERROR;
			}
			
			//���õ�ǰ�û�
			String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //������
			Staff operatorStaff =  staffInfo.getStaff(operator);
			if(operatorStaff == null) {
				LoggerServer.danger("����Ա��������");
				return Action.ERROR;
			}
			active.setCurrent(operator);
			
			
			//�ж��Ƿ�����
			List<Node> nodes = staffInfo.getNodes(operator);
			boolean isAllow = resource.isAny(nodes, url);
			if(!isAllow) {
				LoggerServer.danger("Ȩ�޲���");
				return Action.ERROR;
			}
			
			return invocation.invoke();
		}
		

	}
}
