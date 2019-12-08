package org.account.orm.services;

import org.account.web.action.ActionBase;
import org.apache.struts2.ServletActionContext;

public class LoggerServer {
	public static void console(String msg) {
		System.out.println(msg);
	}
	public static void primary(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.PRIMARY, msg});
	}
	public static void info(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.INFO, msg});
	}
	public static void success(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.SUCESS, msg});
	}
	public static void warning(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.WARNING, msg});
	}
	public static void danger(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {ActionBase.DANGER, msg});
	}
}
