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

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		try {
			//��ʼ��[HibernateUtil]
			HibernateUtil.init();
			

			//��ʼ������
			ResourceServer resource = new ResourceServer();
			InfoServer info = new InfoServer();
			SecretServer secret = new SecretServer();
			Map<String, Object> session = ServletActionContext.getContext().getSession();
			
			HttpServletRequest request = ServletActionContext.getRequest();
			if(!session.containsKey("urlHeader")) {
				session.put("urlHeader", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
			}
			
			ActiveServer<Staff> active = (ActiveServer<Staff>)ServletActionContext.getContext().getApplication().get("active");
			if(active == null) {
				active = new ActiveServer<Staff>();
				ServletActionContext.getContext().getApplication().put("active", active);
			}
			
			
			//ע�����
			Object action = invocation.getAction();
			if(action instanceof IActionable) {		
				LoggerServer.console("IdentityInterceptor: ע��request "+request);
				((IActionable) action).setRequestServer(request);
				LoggerServer.console("IdentityInterceptor: ע��session "+session.hashCode());
				((IActionable) action).setSessionServer(session);
				LoggerServer.console("IdentityInterceptor: ע��active "+active);
				((IActionable) action).setActiveServer(active);
				LoggerServer.console("IdentityInterceptor: ע��secret "+secret);
				((IActionable) action).setSecretServer(secret);
				LoggerServer.console("IdentityInterceptor: ע��info "+info);
				((IActionable) action).setInfoServer(info);
			}

			//��ȡurl
			
			String url = ServletActionContext.getRequest().getRequestURI();
			LoggerServer.console("IdentityInterceptor: ����url "+url);
			boolean isRedirect = url.contains(".action"); 
			if(isRedirect) {
				url = url.replace(".action", "");
			}else {
				LoggerServer.success("");    //��λ��¼��
			}
			session.put("isRedirect", isRedirect);
			LoggerServer.console("IdentityInterceptor: url�ض��� "+isRedirect);			
			
			
			//������Դ
			boolean isPublic =  resource.isPublicResource(url);
			LoggerServer.console("IdentityInterceptor: ������Դ "+isPublic);
			if(isPublic) {
				return invocation.invoke();
			}else {
				
				
				//˽����Դ
				boolean isPrivate = resource.isPrivateResource(url);
				LoggerServer.console("IdentityInterceptor: ������Դ "+isPrivate);
				if(!isPrivate) {
					LoggerServer.danger("������Դ��Ч");
					return Action.ERROR;
				}
				
				
				
				//�����������Ա��
				String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //������
				LoggerServer.console("IdentityInterceptor: ����Ա�� "+operator);
				
				
				
				//�����ݿ��ȡ��ȨԱ����Ϣ
				Staff operatorStaff = info.getHoldStaffInfo(operator);
				LoggerServer.console("IdentityInterceptor: ����Ա��ȷ�� "+operator);
				if(operatorStaff == null) {
					LoggerServer.danger("Ա�������ڻ�δ��Ȩ��¼");
					return Action.ERROR;
				}
				
				
				
				//�ж��Ƿ�����
				List<Node> nodes = info.getStaffNodes(operator);
				boolean isAllow = resource.isAny(nodes, url);
				LoggerServer.console("IdentityInterceptor: Ȩ������Դ "+isAllow);
				if(!isAllow) {
					LoggerServer.danger("Ȩ�޲���");
					return Action.ERROR;
				}
				
				
				//��¼�ж�
				boolean isActive = active.isActive(operatorStaff);
				LoggerServer.console("IdentityInterceptor: �û��ѵ�¼ "+isActive);
				boolean isLogin = url.contains("Home/login");
				LoggerServer.console("IdentityInterceptor: ��¼���� "+isLogin);
				if( !isActive && !isLogin) {
					LoggerServer.danger("Ա��"+operatorStaff.getNumber()+"δ��¼");
					return Action.ERROR;
				}
				
				
				//���õ�ǰԱ��
				active.setCurrent(operatorStaff);
				LoggerServer.console("IdentityInterceptor: ��ǰ��¼Ա�� "+operatorStaff);
				return invocation.invoke();
			}
		}catch(Exception e) {
			LoggerServer.danger(e.getMessage());
			return Action.ERROR;
		}
		
		

	}
}
