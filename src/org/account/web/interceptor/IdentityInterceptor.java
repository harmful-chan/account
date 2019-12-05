package org.account.web.interceptor;

import java.util.Map;

import org.account.orm.SystemManagement;
import org.account.util.HibernateUtil;
import org.account.util.JDBCUtil;
import org.account.util.LogUtil;
import org.account.web.action.ActionBase;
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
		new HibernateUtil();
		new JDBCUtil();
		
		//ע��SystemManagement
		Map<String, Object> app = ServletActionContext.getContext().getApplication();
		SystemManagement sm = (SystemManagement) app.get("management");
		if( sm == null ) {
			sm = new SystemManagement();
			app.put("management", sm);
		}

		boolean ret = false;
		//��ȡurl
		String url = ServletActionContext.getRequest().getRequestURI();
		if(url.contains(".action")) {
			url.replace(".action", "");
		}
		LogUtil.Log("[����url]:"+url);
		
		
		
		
		
		//��Դ��Ч��֤
		ret = sm.isResource(url);
		if(!ret) {
			ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "����url������"});
			return Action.ERROR;
		}
		LogUtil.Log("[�ڲ�url]:"+ret);
		
		
		
		
		
		//��Դ˽����֤
		ret = sm.isPrivateResource(url);
		LogUtil.Log("[�ڲ�˽��]:"+ret);
		
		String number = (String)ServletActionContext.getRequest().getParameter("number");
		if(ret) {

			//Ȩ����֤
			ret = sm.isAllow(number, url);
			if(!ret) {
				ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "Ա��"+number+"Ȩ�޲���"});
				return Action.ERROR;
			}
			LogUtil.Log("[����ͨ��]:"+ret);
			
			
			
			
			
			///��֤�Ƿ��¼����
			//��֤�Ƿ��¼�û�
			ret = sm.isActiveStaff(number);
			if(!ret && !url.contains("Home/login")) {
				ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "Ա��"+number+"δ��½"});
				return Action.ERROR;
			}
			LogUtil.Log("[�û��Ƿ��ѵ�¼]:"+ret);		

			
			//���õ�ǰ�û�
			sm.setCurrentActiveStaff(number);
			LogUtil.Log("[��ǰ�����û�]:"+number);
		}
		
		

		
		//ע��ʵ��
		Object action = invocation.getAction();
		if(action instanceof IActionable) {
			((IActionable) action).setSystemManagement(sm);
			((IActionable) action).setRequest(ServletActionContext.getRequest());		
			((IActionable) action).setSession(ServletActionContext.getContext().getSession());		
		}
		LogUtil.Log("[Action]:ע��SystemManagement, Request");

	
		return invocation.invoke();
	}

}
