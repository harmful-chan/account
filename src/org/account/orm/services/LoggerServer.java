package org.account.orm.services;

import org.account.web.action.ActionBase;
import org.apache.struts2.ServletActionContext;

public class LoggerServer {
	
	public static final String PRIMARY  = "primary";
	public static final String INFO  = "info";
	public static final String SUCESS  = "success";
	public static final String WARNING  = "warning";
	public static final String DANGER  = "danger";
	
	//"purple | blue | green | orange | red"
	public static void console(Object msg) {
		System.out.println(msg);
	}
	public static void primary(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {PRIMARY, msg, "purple"});
	}
	public static void info(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {INFO, msg, "blue"});
	}
	public static void success(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {SUCESS, msg, "green"});
	}
	public static void warning(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {WARNING, msg, "orange"});
	}
	public static void danger(String msg) {
		ServletActionContext.getContext().getSession().put("msg", new String[] {DANGER, msg, "red"});
	}
}
