package org.account.web.interceptor;

import java.util.Map;

import org.account.orm.SystemManagement;
import org.account.util.HibernateUtil;
import org.account.util.LogUtil;
import org.account.web.action.ActionBase;
import org.account.web.action.IActionable;
import org.apache.struts2.ServletActionContext;

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
		
		//com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor
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
		LogUtil.Log("[����url]:"+url);
		
		//��Դ��Ч��֤
		ret = sm.isResource(url);
		if(!ret) {
			ServletActionContext.getRequest().setAttribute("msg", new String[] {"����", "����url������"});
			return "login";
		}
		LogUtil.Log("[isResource]:"+ret);
		
		//��Դ˽����֤
		ret = sm.isPrivateResource(url);
		LogUtil.Log("[isPrivateResource]:"+ret);
		if(ret) {
			String number = (String)ServletActionContext.getRequest().getParameter("number");
			
			//Ȩ����֤
			ret = sm.isAllow(number, url);
			if(!ret) {
				ServletActionContext.getRequest().setAttribute("msg", new String[] {"����", "Ա��"+number+"Ȩ�޲���"});
				return "login";
			}
			LogUtil.Log("[isAllow]:"+ret);
			
			///��֤�Ƿ��¼����
			//��֤�Ƿ��¼�û�
			ret = sm.isActiveStaff(number);
			if(!ret && !url.contains("Home/login")) {
				ServletActionContext.getRequest().setAttribute("msg", new String[] {"����", "Ա��"+number+"δ��½"});
				return "login";
			}
			LogUtil.Log("[isActiveStaff]:"+ret);
			
			//���õ�ǰ�û�
			sm.setCurrentActiveStaff(number);
			LogUtil.Log("[setCurrentActiveStaff]:"+number);
			
			//ע��ʵ��
			Object action = invocation.getAction();
			if(action instanceof IActionable) {
				((IActionable) action).setSystemManagement(sm);
				((IActionable) action).setRequest(ServletActionContext.getRequest());				
			}

			LogUtil.Log("[getCurrentActiveStaff]:ע��ʵ��");
		}
		
		return invocation.invoke();
	}

}
