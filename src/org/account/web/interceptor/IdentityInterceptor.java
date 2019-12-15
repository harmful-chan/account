package org.account.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.account.orm.bean.Node;
import org.account.orm.bean.Staff;
import org.account.orm.services.*;
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

	private void putInApp(String key, Object o) {
		Object object = ServletActionContext.getContext().getApplication().get(key);
		if(object == null) {
			ServletActionContext.getContext().getApplication().put(key, o);
		}
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//��ʼ��[HibernateUtil]

		HibernateUtil.init();
		

		
		//��ʼ������
		
		ResourceServer resource = new ResourceServer();
		InfoServer info = new InfoServer();
		SecretServer secret = new SecretServer();
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		ActiveServer<Staff> active = (ActiveServer<Staff>)ServletActionContext.getContext().getApplication().get("active");
		if(active == null) {
			active = new ActiveServer<Staff>();
			ServletActionContext.getContext().getApplication().put("active", active);
		}
		//ע�����
		Object action = invocation.getAction();
		if(action instanceof IActionable) {		
			((IActionable) action).setRequestServer(request);
			((IActionable) action).setSessionServer(session);
			((IActionable) action).setActiveServer(active);
			((IActionable) action).setSecretServer(secret);
			((IActionable) action).setInfoServer(info);
		}

		//��ȡurl
		String url = ServletActionContext.getRequest().getRequestURI();
		if(url.contains(".action")) {
			url = url.replace(".action", "");
			session.put("isRedirect", true);
		}else {
			session.put("isRedirect", false);
			LoggerServer.success("");    //��λ��ְ��¼
		}
		
		try {
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
				
				
				String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //������
				Staff operatorStaff =  info.getStaffInfo(operator);
				if(operatorStaff == null) {
					LoggerServer.danger("Ա��������");
					return Action.ERROR;
				}
				
				if(!info.isHoldStaff(operatorStaff.getNumber())){
					LoggerServer.danger("Ա��"+operatorStaff.getNumber()+"δ����Ȩ��¼");
					return Action.ERROR;
				}
				
				//�ж��Ƿ�����
				List<Node> nodes = info.getStaffNodes(operator);
				boolean isAllow = resource.isAny(nodes, url);
				if(!isAllow) {
					LoggerServer.danger("Ȩ�޲���");
					return Action.ERROR;
				}
				
				
				//��¼�ж�
				if( !active.isActive(operatorStaff) && !url.contains("Home/login")) {
					LoggerServer.danger("Ա��"+operatorStaff.getNumber()+"δ��¼");
					return Action.ERROR;
				}
				
				//���õ�ǰ�û�
				active.setCurrent(operatorStaff);
				return invocation.invoke();
			}
		}catch(Exception e) {
			LoggerServer.danger(e.getMessage());
			return Action.ERROR;
		}
		
		

	}
}
