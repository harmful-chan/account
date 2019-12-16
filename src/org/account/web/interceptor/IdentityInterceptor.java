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
			//初始化[HibernateUtil]
			HibernateUtil.init();
			

			//初始化服务
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
			
			
			//注入服务
			Object action = invocation.getAction();
			if(action instanceof IActionable) {		
				LoggerServer.console("IdentityInterceptor: 注入request "+request);
				((IActionable) action).setRequestServer(request);
				LoggerServer.console("IdentityInterceptor: 注入session "+session.hashCode());
				((IActionable) action).setSessionServer(session);
				LoggerServer.console("IdentityInterceptor: 注入active "+active);
				((IActionable) action).setActiveServer(active);
				LoggerServer.console("IdentityInterceptor: 注入secret "+secret);
				((IActionable) action).setSecretServer(secret);
				LoggerServer.console("IdentityInterceptor: 注入info "+info);
				((IActionable) action).setInfoServer(info);
			}

			//获取url
			
			String url = ServletActionContext.getRequest().getRequestURI();
			LoggerServer.console("IdentityInterceptor: 请求url "+url);
			boolean isRedirect = url.contains(".action"); 
			if(isRedirect) {
				url = url.replace(".action", "");
			}else {
				LoggerServer.success("");    //复位记录器
			}
			session.put("isRedirect", isRedirect);
			LoggerServer.console("IdentityInterceptor: url重定向 "+isRedirect);			
			
			
			//共有资源
			boolean isPublic =  resource.isPublicResource(url);
			LoggerServer.console("IdentityInterceptor: 公有资源 "+isPublic);
			if(isPublic) {
				return invocation.invoke();
			}else {
				
				
				//私有资源
				boolean isPrivate = resource.isPrivateResource(url);
				LoggerServer.console("IdentityInterceptor: 限制资源 "+isPrivate);
				if(!isPrivate) {
					LoggerServer.danger("访问资源无效");
					return Action.ERROR;
				}
				
				
				
				//从请请求操作员工
				String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //操作人
				LoggerServer.console("IdentityInterceptor: 操作员工 "+operator);
				
				
				
				//从数据库获取授权员工信息
				Staff operatorStaff = info.getHoldStaffInfo(operator);
				LoggerServer.console("IdentityInterceptor: 操作员工确认 "+operator);
				if(operatorStaff == null) {
					LoggerServer.danger("员工不存在或未授权登录");
					return Action.ERROR;
				}
				
				
				
				//判断是否允许
				List<Node> nodes = info.getStaffNodes(operator);
				boolean isAllow = resource.isAny(nodes, url);
				LoggerServer.console("IdentityInterceptor: 权限内资源 "+isAllow);
				if(!isAllow) {
					LoggerServer.danger("权限不足");
					return Action.ERROR;
				}
				
				
				//登录判断
				boolean isActive = active.isActive(operatorStaff);
				LoggerServer.console("IdentityInterceptor: 用户已登录 "+isActive);
				boolean isLogin = url.contains("Home/login");
				LoggerServer.console("IdentityInterceptor: 登录操作 "+isLogin);
				if( !isActive && !isLogin) {
					LoggerServer.danger("员工"+operatorStaff.getNumber()+"未登录");
					return Action.ERROR;
				}
				
				
				//设置当前员工
				active.setCurrent(operatorStaff);
				LoggerServer.console("IdentityInterceptor: 当前登录员工 "+operatorStaff);
				return invocation.invoke();
			}
		}catch(Exception e) {
			LoggerServer.danger(e.getMessage());
			return Action.ERROR;
		}
		
		

	}
}
