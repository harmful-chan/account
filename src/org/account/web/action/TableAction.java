package org.account.web.action;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.bean.*;
import org.account.web.bean.TableContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

public class TableAction extends ActionBase implements ModelDriven<TableContext>{

	private static final long serialVersionUID = 1L;

	private TableContext tableContext = new TableContext();
	List<TableContext> tables;
	
	
	@Override
	public TableContext getModel() {
		return this.tableContext;
	}
	
	public TableAction(){
		this.tables = new ArrayList<TableContext>();
	}
	
	
	
	/**
	 * 往tables添加部门所属账号
	 * @param departmentName
	 * @throws Exception 
	 */
	private void joinInterior(Department department) throws Exception {
		List<Account>  accounts = info.getShareAccountList(department.getName());
		
		for (Account account : accounts) {
			TableContext t = new TableContext();
			t.setAccountNumber(account.getNumber());
			t.setDeeppwd(secret.encode(account.getPassword(), account.getSalf()));
			t.setOwnerNumber("---");
			t.setOwnerRole("---");
			t.setOwnerDepartment(department.getExplain());
			t.setExplain(account.getExplain());
			t.setValid("");			
			tables.add(t);
		}
		
	}
	
	@SkipValidation
	public String getInterior() throws Exception {
		Staff activeStaff = active.getCurrent();
		joinInterior(activeStaff.getDepartment());
		session.put("table_info", tables);
		flashUrl(activeStaff);
		return SUCCESS;
	}
	
	
	public String alterInterior() throws Exception {
		Department  department = active.getCurrent().getDepartment();
		//密码解析
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//构建Account
		Account account = new Account();
		account.setNumber(tableContext.getAccountNumber());
		account.setPassword(password);
		account.setSalf(salf);
		account.setExplain(tableContext.getExplain());
		
		//Account 存在 修改
		//Account 不存在 添加
		info.addShareAccount(account, department.getName());
		return getInterior();
	}
	
	
	public String removeInterior() throws Exception {
		Staff activeStaff = active.getCurrent();
		info.removeShareAccount(tableContext.getAccountNumber(), activeStaff.getDepartment().getName());
		return getInterior();
	}
	
	/**
	 * 把持有账号员工信息添加进tables
	 * @throws Exception 
	 */
	private void joinSuper() throws Exception {
		List<Staff> holdStaffs = info.getHoldStaffList();
		for (Staff staff : holdStaffs) {
			Account a = staff.getAccount();
			TableContext t = new TableContext();
			t.setAccountNumber(a.getNumber());
			t.setDeeppwd(secret.encode(a.getPassword(), a.getSalf()));
			t.setExplain(a.getExplain());
			t.setOwnerNumber(staff.getNumber());
			t.setOwnerRole(staff.getRole().getExplain());
			t.setOwnerDepartment(staff.getDepartment().getExplain());
			t.setValid(secret.isValid(a) ? "有效" : "无效");
			tables.add(t);
		}
	}
	
	@SkipValidation
	public String getSuper() throws Exception {
		joinSuper();
		session.put("table_info", tables);
		Staff activeStaff = active.getCurrent();
		flashUrl(activeStaff);
		return SUCCESS;
	}
	

	public String alterSuper() throws Exception {
		//密码解析
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//构建Account信息
		String ownerNumber = this.tableContext.getOwnerNumber();
		Account newAccount = new Account();
		newAccount.setNumber(this.tableContext.getAccountNumber());
		newAccount.setPassword(password);
		newAccount.setSalf(salf);
		newAccount.setExplain(this.tableContext.getExplain());
		//存在，修改
		//不存在，添加
		info.addHoldStaff(ownerNumber, newAccount);
		return getSuper();
	}
	
	
	public String removeSuper() throws Exception {
		info.removeHoldStaffAccount(tableContext.getOwnerNumber());
		return getSuper();
	}
	
	
	
	
	@SkipValidation
	public String getRoot() throws Exception {
		
		
		//添加所有持号员工
		joinSuper();
		//添加所有部门
		List<Department> departments = info.getDepartments();
		for (Department department : departments) {
			joinInterior(department);
			
		}
		
		
		session.put("table_info", tables);
		Staff activeStaff = active.getCurrent();
		flashUrl(activeStaff);
		return SUCCESS;
	}
	
	/**
	 * 用是否传入工号确定添加是什么账号
	 * @return
	 * @throws Exception 
	 */
	public String alterRoot() throws Exception {
		//登录用户
		Staff activeStaff = active.getCurrent();
		//密码解析
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//Account信息
		String ownerNumber = this.tableContext.getOwnerNumber();
		Account newAccount = new Account();
		newAccount.setNumber(this.tableContext.getAccountNumber());
		newAccount.setPassword(password);
		newAccount.setSalf(salf);
		newAccount.setExplain(this.tableContext.getExplain());
		
		if(tableContext.getOwnerNumber().isEmpty()) {    //添加本部门账号
			info.addShareAccount(newAccount, activeStaff.getDepartment().getName());
		}else {
			info.addHoldStaff(ownerNumber, newAccount);
		}
		return getRoot();
	}
	
	public String removeRoot() throws Exception {
		if(info.isHoldStaff(tableContext.getOwnerNumber())) {    //员工持有账号
			info.removeHoldStaffAccount(tableContext.getOwnerNumber());
		}else {    //部门共享账号
			List<Department> departments = info.getDepartments();
			for (Department department : departments) {
				if(info.isShareAccount(tableContext.getAccountNumber(), department.getName())) {
					info.removeShareAccount(tableContext.getAccountNumber(), department.getName());
				}
				
			}
		}
		return getRoot();
	}
	
}
