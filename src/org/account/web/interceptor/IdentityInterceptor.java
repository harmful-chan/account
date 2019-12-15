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
		//初始化[HibernateUtil]

		HibernateUtil.init();
		

		
		//初始化服务
		
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
		//注入服务
		Object action = invocation.getAction();
		if(action instanceof IActionable) {		
			((IActionable) action).setRequestServer(request);
			((IActionable) action).setSessionServer(session);
			((IActionable) action).setActiveServer(active);
			((IActionable) action).setSecretServer(secret);
			((IActionable) action).setInfoServer(info);
		}

		//获取url
		String url = ServletActionContext.getRequest().getRequestURI();
		if(url.contains(".action")) {
			url = url.replace(".action", "");
			session.put("isRedirect", true);
		}else {
			session.put("isRedirect", false);
			LoggerServer.success("");    //复位入职记录
		}
		
		try {
			//共有资源
			boolean isPublic =  resource.isPublicResource(url);
			if(isPublic) {
				return invocation.invoke();
			}else {
				//私有资源
				boolean isPrivate = resource.isPrivateResource(url);
				if(!isPrivate) {
					LoggerServer.danger("访问资源无效");
					return Action.ERROR;
				}
				
				
				String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //操作人
				Staff operatorStaff =  info.getStaffInfo(operator);
				if(operatorStaff == null) {
					LoggerServer.danger("员工不存在");
					return Action.ERROR;
				}
				
				if(!info.isHoldStaff(operatorStaff.getNumber())){
					LoggerServer.danger("员工"+operatorStaff.getNumber()+"未被授权登录");
					return Action.ERROR;
				}
				
				//判断是否允许
				List<Node> nodes = info.getStaffNodes(operator);
				boolean isAllow = resource.isAny(nodes, url);
				if(!isAllow) {
					LoggerServer.danger("权限不足");
					return Action.ERROR;
				}
				
				
				//登录判断
				if( !active.isActive(operatorStaff) && !url.contains("Home/login")) {
					LoggerServer.danger("员工"+operatorStaff.getNumber()+"未登录");
					return Action.ERROR;
				}
				
				//设置当前用户
				active.setCurrent(operatorStaff);
				return invocation.invoke();
			}
		}catch(Exception e) {
			LoggerServer.danger(e.getMessage());
			return Action.ERROR;
		}
		
		

	}
}
