package org.account.web.interceptor;

import java.util.List;
import java.util.Map;

import org.account.orm.SystemManagement;
import org.account.orm.model.Node;
import org.account.orm.services.*;
import org.account.orm.services.StaffInfoServer;
import org.account.util.HibernateUtil;
import org.account.util.JDBCUtil;
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
		HibernateUtil.init();
		JDBCUtil.init();
		

		
		//初始化服务
		ResourceServer resource = new ResourceServer();
		AssignedServer assigned = new AssignedServer();
		StaffInfoServer staffInfo = new StaffInfoServer();
		ActiveServer<String> active = new ActiveServer<String>();
		
		String url = ServletActionContext.getRequest().getRequestURI();
		String opera = (String)ServletActionContext.getRequest().getParameter("number");    //操作人
		
		//设置当前用户
		active.setCurrent(opera);
		
		
		//共有资源
		boolean isPublic =  resource.isPublicResource(url);
		if(isPublic) {
			invocation.invoke();
		}
		
		//私有资源
		boolean isPrivate = resource.isPrivateResource(url);
		if(!isPrivate) {
			LoggerServer.danger("访问资源无效");
			return Action.ERROR;
		}
		
		
		//判断是否允许
		List<Node> nodes = staffInfo.getNodes(opera);
		boolean isAllow = resource.isAny(nodes, url);
		if(!isAllow) {
			LoggerServer.danger("权限不足");
			return Action.ERROR;
		}
		
		
		boolean isLogin = url.contains("Home/login");
		if(isLogin) {
			invocation.invoke();
		}
		
		
		return invocation.invoke();
	}

}
