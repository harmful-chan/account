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
		//初始化[HibernateUtil]
		new HibernateUtil();
		new JDBCUtil();
		
		//注入SystemManagement
		Map<String, Object> app = ServletActionContext.getContext().getApplication();
		SystemManagement sm = (SystemManagement) app.get("management");
		if( sm == null ) {
			sm = new SystemManagement();
			app.put("management", sm);
		}

		boolean ret = false;
		//获取url
		String url = ServletActionContext.getRequest().getRequestURI();
		if(url.contains(".action")) {
			url.replace(".action", "");
		}
		LogUtil.Log("[请求url]:"+url);
		
		
		
		
		
		//资源有效验证
		ret = sm.isResource(url);
		if(!ret) {
			ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "访问url不存在"});
			return Action.ERROR;
		}
		LogUtil.Log("[内部url]:"+ret);
		
		
		
		
		
		//资源私有验证
		ret = sm.isPrivateResource(url);
		LogUtil.Log("[内部私有]:"+ret);
		
		String number = (String)ServletActionContext.getRequest().getParameter("number");
		if(ret) {

			//权限验证
			ret = sm.isAllow(number, url);
			if(!ret) {
				ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "员工"+number+"权限不足"});
				return Action.ERROR;
			}
			LogUtil.Log("[允许通过]:"+ret);
			
			
			
			
			
			///验证是否登录操作
			//验证是否登录用户
			ret = sm.isActiveStaff(number);
			if(!ret && !url.contains("Home/login")) {
				ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, "员工"+number+"未登陆"});
				return Action.ERROR;
			}
			LogUtil.Log("[用户是否已登录]:"+ret);		

			
			//设置当前用户
			sm.setCurrentActiveStaff(number);
			LogUtil.Log("[当前访问用户]:"+number);
		}
		
		

		
		//注入实例
		Object action = invocation.getAction();
		if(action instanceof IActionable) {
			((IActionable) action).setSystemManagement(sm);
			((IActionable) action).setRequest(ServletActionContext.getRequest());		
			((IActionable) action).setSession(ServletActionContext.getContext().getSession());		
		}
		LogUtil.Log("[Action]:注入SystemManagement, Request");

	
		return invocation.invoke();
	}

}
