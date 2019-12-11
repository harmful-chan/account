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
		//初始化[HibernateUtil]

		HibernateUtil.init();
		

		
		//初始化服务
		ResourceServer resource = new ResourceServer();
		StaffInfoServer staffInfo = new StaffInfoServer();
		ActiveServer<String> active = new ActiveServer<String>();
		EncryptedServer encryption = new EncryptedServer();
		SecretServer secret = new SecretServer();
		String url = ServletActionContext.getRequest().getRequestURI();

		//注入服务
		Object action = invocation.getAction();
		if(action instanceof IActionable) {		
			((IActionable) action).setSessionServer(ServletActionContext.getContext().getSession());
			((IActionable) action).setActiveServer(active);
			((IActionable) action).setSecretServer(secret);
			((IActionable) action).setEncryptedServer(encryption);
			((IActionable) action).setStaffInfoServer(staffInfo);
		}
		
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
			
			//设置当前用户
			String operator = (String)ServletActionContext.getRequest().getParameter("operator");    //操作人
			Staff operatorStaff =  staffInfo.getStaff(operator);
			if(operatorStaff == null) {
				LoggerServer.danger("操作员工不存在");
				return Action.ERROR;
			}
			active.setCurrent(operator);
			
			
			//判断是否允许
			List<Node> nodes = staffInfo.getNodes(operator);
			boolean isAllow = resource.isAny(nodes, url);
			if(!isAllow) {
				LoggerServer.danger("权限不足");
				return Action.ERROR;
			}
			
			return invocation.invoke();
		}
		

	}
}
